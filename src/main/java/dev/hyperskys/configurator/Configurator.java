/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator;

import dev.hyperskys.configurator.annotations.GetValue;
import dev.hyperskys.configurator.api.exception.PluginNotFoundException;
import dev.hyperskys.configurator.utils.FileUtils;
import dev.hyperskys.configurator.utils.ReflectionUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

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
        pluginProvided = instance;
        if (instance == null) throw new PluginNotFoundException();
        ReflectionUtils.disableReflectionsLogger();
        FileUtils.updateFiles();

        instance.getServer().getScheduler().runTaskTimer(instance, () -> {
            for (Field field : ReflectionUtils.getFieldsAnnotated(GetValue.class, Configurator.getPluginProvided().getClass().getPackage().getName())) {
                String fileProvided = field.getAnnotation(GetValue.class).file();
                String pathOfValue = field.getAnnotation(GetValue.class).path();

                if (FileUtils.findConfiguration(fileProvided, Configurator.getPluginProvided()).get(pathOfValue) != null) {
                    field.setAccessible(true);
                    FileUtils.updateFiles();
                }
            }
        }, 0, 20*2);
    }
}
