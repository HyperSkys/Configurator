/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator;

import dev.hyperskys.configurator.api.exception.ObjectNotFoundException;
import dev.hyperskys.configurator.api.exception.PluginNotFoundException;
import dev.hyperskys.configurator.utils.FileUtils;
import dev.hyperskys.configurator.utils.ReflectionUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;

/**
 * The configurator class for initializing all things to do with Configurator.
 * @since 1.0.0-RELEASE
 * @version 2.0.1-STABLE
 */
public class Configurator {

    private @Getter static Plugin pluginProvided;

    /**
     * Set up of the projects global variables that is used for configurator.
     * @param instance An instance of the main plugin.
     */
    @SneakyThrows
    public static void setupConfigurator(Plugin instance) {
        ReflectionUtils.disableReflectionsLogger();
        if (instance == null) throw new PluginNotFoundException();
        pluginProvided = instance;

        try { FileUtils.updateFiles(); }
        catch (ObjectNotFoundException ignored) {}
        instance.getServer().getScheduler().runTaskTimer(instance, FileUtils::updateFiles, 20*2, 20*2);
    }
}
