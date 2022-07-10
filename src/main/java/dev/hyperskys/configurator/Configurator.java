/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class Configurator {

    @Getter
    private static Plugin plugin;

    public static void setupConfigurator(Plugin instance) {
        plugin = instance;
    }
}
