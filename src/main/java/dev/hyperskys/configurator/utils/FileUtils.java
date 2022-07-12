package dev.hyperskys.configurator.utils;

import dev.hyperskys.configurator.api.exception.ObjectNotFoundException;
import org.bukkit.configuration.file.YamlConfiguration;

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
    public static YamlConfiguration findConfiguration(String fileName) {
        throw new ObjectNotFoundException(fileName);
    }
}
