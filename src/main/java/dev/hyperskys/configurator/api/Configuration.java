/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.api;

import dev.hyperskys.configurator.Configurator;
import dev.hyperskys.configurator.events.ConfigurationReloadEvent;
import dev.hyperskys.configurator.events.ConfigurationSaveEvent;
import dev.hyperskys.configurator.output.ConfiguratorLogger;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Configuration {

    private static File file;
    private static FileConfiguration customFile;
    private final Plugin plugin = Configurator.getPlugin();

    @Getter
    private final String nameFile;

    @Getter
    private long reloadTime = 0;

    @Getter
    private long saveTime = 0;

    @Setter
    private String failCreateNewFileMessage = "[" + plugin.getDescription().getName() + "] Failed to correct a new file, maybe it already exists?";

    @Setter
    private String failSaveConfigurationMessage = "[" + plugin.getDescription().getName() + "] Failed to save the configuration file, error log is below.";

    public Configuration(String fileName) {
        nameFile = fileName;
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        setup();
        get().options().copyDefaults(true);
        save();
    }

    public void setup() {
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(plugin.getDescription().getName())).getDataFolder(), nameFile);

        if (!file.exists()){
            try {
                if(!file.createNewFile()) {
                    ConfiguratorLogger.logMessage(failCreateNewFileMessage);
                }
            } catch (IOException ignored) {}
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get() {
        return customFile;
    }

    public void save() {
        ConfigurationSaveEvent configurationSaveEvent = new ConfigurationSaveEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(configurationSaveEvent);

        if (!configurationSaveEvent.isCancelled()) {
            try {
                long startTime = System.currentTimeMillis();
                customFile.save(file);
                saveTime = (System.currentTimeMillis() - startTime) / 1000;
            } catch (IOException e){
                ConfiguratorLogger.logMessage(failSaveConfigurationMessage);
                e.printStackTrace();
            }
        }
    }

    public void reload(){
        ConfigurationReloadEvent configurationReloadEvent = new ConfigurationReloadEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(configurationReloadEvent);
        if (!configurationReloadEvent.isCancelled()) {
            long startTime = System.currentTimeMillis();
            customFile = YamlConfiguration.loadConfiguration(file);
            reloadTime = (System.currentTimeMillis() - startTime) / 1000;
        }
    }
}