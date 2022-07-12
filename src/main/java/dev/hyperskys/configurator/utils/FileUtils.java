package dev.hyperskys.configurator.utils;

import dev.hyperskys.configurator.api.exception.ObjectNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Objects;

/**
 * Utilises for Java Files and other things you want to do with files
 * @since 2.0.0-RELEASE
 */
public class FileUtils {
    /**
     * Allows you to find YamlConfiguration with just a file name
     * @param fileName The name of the file you want to find.
     * @return The YamlConfiguration of the file.
     */
    public static YamlConfiguration findConfiguration(String fileName, Plugin plugin) {
        File file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(plugin.getDescription().getName())).getDataFolder(), fileName);
        if (file.exists()) {
            return YamlConfiguration.loadConfiguration(file);
        }

        throw new ObjectNotFoundException(fileName);
    }
}
