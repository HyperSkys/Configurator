/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.output;

import org.bukkit.Bukkit;

public class ConfiguratorLogger {
    public static void logMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
