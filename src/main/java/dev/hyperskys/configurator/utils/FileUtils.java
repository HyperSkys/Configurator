package dev.hyperskys.configurator.utils;

import dev.hyperskys.configurator.Configurator;
import dev.hyperskys.configurator.annotations.GetValue;
import dev.hyperskys.configurator.api.exception.ObjectNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.lang.reflect.Field;
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

    /**
     * Updates the fields with all their correct configuration values.
     */
    public static void updateFiles() {
        for (Field field : ReflectionUtils.getFieldsAnnotated(GetValue.class, Configurator.getPluginProvided().getClass().getPackage().getName())) {
            String fileProvided = field.getAnnotation(GetValue.class).file();
            String pathOfValue = field.getAnnotation(GetValue.class).path();

            if (FileUtils.findConfiguration(fileProvided, Configurator.getPluginProvided()).get(pathOfValue) != null) {
                field.setAccessible(true);
                try {
                    field.set(null, FileUtils.findConfiguration(fileProvided, Configurator.getPluginProvided()).get(pathOfValue));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
