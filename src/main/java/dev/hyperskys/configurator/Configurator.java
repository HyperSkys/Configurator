/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator;

import dev.hyperskys.configurator.annotations.GetValue;
import dev.hyperskys.configurator.utils.FileUtils;
import dev.hyperskys.configurator.utils.ReflectionUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

/**
 * The configurator class for initializing all things to do with Configurator.
 * @since 1.0.0-RELEASE
 * @version 2.0.0-RELEASE
 * @// TODO: 7/12/2022 Grab an instance of configuration file, also get an instance of class that has the field in it and grabbing that field.
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

        for (Field field : ReflectionUtils.getFieldsAnnotated(GetValue.class, instance.getClass().getPackage().getName())) {
            String fileProvided = field.getAnnotation(GetValue.class).file();
            String pathOfValue = field.getAnnotation(GetValue.class).path();

            field.setAccessible(true);
            field.set(null, FileUtils.findConfiguration(fileProvided, instance).get(pathOfValue));
        }
    }
}
