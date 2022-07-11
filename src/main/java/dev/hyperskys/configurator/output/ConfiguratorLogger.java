/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.output;

import org.bukkit.Bukkit;

public class ConfiguratorLogger {
    /**
     * This is just a simple logger to make logging messages much quicker in production.
     * @param message The message that you want to log the console
     */
    public static void logMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
