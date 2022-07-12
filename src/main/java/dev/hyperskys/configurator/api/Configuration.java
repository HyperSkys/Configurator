/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.api;

import dev.hyperskys.configurator.Configurator;
import dev.hyperskys.configurator.annotations.GetValue;
import dev.hyperskys.configurator.api.exception.ObjectAlreadyExistsException;
import dev.hyperskys.configurator.api.exception.ObjectNotFoundException;
import dev.hyperskys.configurator.events.ConfigurationReloadEvent;
import dev.hyperskys.configurator.events.ConfigurationSaveEvent;
import dev.hyperskys.configurator.output.ConfiguratorLogger;
import dev.hyperskys.configurator.utils.FileUtils;
import dev.hyperskys.configurator.utils.ReflectionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * The configuration file and the primary part of Configurator.
 * @since 1.0.0-RELEASE
 */
public class Configuration {

    private static File file;
    private static FileConfiguration customFile;
    private static Plugin plugin;

    private @Getter final String nameFile;
    private @Getter long reloadTime = 0;
    private @Getter long saveTime = 0;

    public static @Setter String failedToSave;
    public static @Setter String failedToCreate;

    /**
     * The constructor for creating the configuration file, no need to initialize it's already done here.
     * @param fileName The file you would like to create (ex: config.yml).
     */
    public Configuration(String fileName) {
        nameFile = fileName;
    }

    /**
     * Creates the configuration file and stuff, yeah.
     */
    @SneakyThrows
    public void init() {
        plugin = Configurator.getPluginProvided();
        failedToCreate = "[" + plugin.getDescription().getName() + "] Failed to correct a new file, maybe it already exists?";
        failedToSave = "[" + plugin.getDescription().getName() + "] Failed to save the configuration file, error log is below.";

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        setup();
        get().options().copyDefaults(true);
        save();
    }

    private void setup() {
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(plugin.getDescription().getName())).getDataFolder(), nameFile);

        if (!file.exists()) {
            try {
                if(!file.createNewFile()) {
                    ConfiguratorLogger.logMessage(failedToCreate);
                }
            } catch (IOException ignored) {}
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * This will allow you to grab values for the config while being safe from NullPointers.
     * @return The stored value in the config designated by the path.
     */
    public Object getValue(String path) {
        if (customFile.get(path) != null) return customFile.get(path);
        throw new ObjectNotFoundException(path);
    }

    /**
     * This will allow you to set values for the config while being safe from NullPointers.
     */
    public void setValue(String path, String value) {
        if (customFile.get(path) != null) customFile.set(path, value);
        throw new ObjectNotFoundException(path);
    }

    public void createSection(String section) {
        if (customFile.getConfigurationSection(section) != null) customFile.createSection(section);
        throw new ObjectAlreadyExistsException(section);
    }

    /**
     * This will allow you to access the features of the YamlConfiguration, such as setting and getting configuration values.
     * @return An Instance of the YamlConfiguration
     */
    public FileConfiguration get() {
        return customFile;
    }

    /**
     * This saves the configuration file, please save it after changing configuration contents,
     * working on an auto save feature,
     * will be here soon.
     */
    public void save() {
        ConfigurationSaveEvent configurationSaveEvent = new ConfigurationSaveEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(configurationSaveEvent);

        if (!configurationSaveEvent.isCancelled()) {
            try {
                long startTime = System.currentTimeMillis();
                customFile.save(file);
                saveTime = (System.currentTimeMillis() - startTime) / 1000;
            } catch (IOException e) {
                ConfiguratorLogger.logMessage(failedToSave);
                e.printStackTrace();
            }
        }
    }

    /**
     * Reloads the configuration file, allows the plugin to see the new updated contents of the file.
     */
    @SneakyThrows
    public void reload() {
        ConfigurationReloadEvent configurationReloadEvent = new ConfigurationReloadEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(configurationReloadEvent);
        if (!configurationReloadEvent.isCancelled()) {
            long startTime = System.currentTimeMillis();
            customFile = YamlConfiguration.loadConfiguration(file);
            reloadTime = (System.currentTimeMillis() - startTime) / 1000;

            for (Field field : ReflectionUtils.getFieldsAnnotated(GetValue.class, Configurator.getPluginProvided().getClass().getPackage().getName())) {
                String fileProvided = field.getAnnotation(GetValue.class).file();
                String pathOfValue = field.getAnnotation(GetValue.class).path();

                if (FileUtils.findConfiguration(fileProvided, Configurator.getPluginProvided()).get(pathOfValue) != null) {
                    field.setAccessible(true);
                    field.set(null, FileUtils.findConfiguration(fileProvided, Configurator.getPluginProvided()).get(pathOfValue));
                }
            }
        }
    }
}