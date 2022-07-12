/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator;

import dev.hyperskys.configurator.annotations.GetValue;
import dev.hyperskys.configurator.api.Configuration;
import dev.hyperskys.configurator.utils.ReflectionUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * The configurator class for initializing all things to do with Configurator.
 * @version 1.0.8-BETA
 */
public class Configurator {

    private @Getter static Plugin pluginProvided;
    public static HashMap<String, Configuration> listOfFiles = new HashMap<>();

    /**
     * Set up of the projects global variables that is used for configurator.
     * @param instance An instance of the main plugin.
     */
    @SneakyThrows
    public static void setupConfigurator(Plugin instance) {
        pluginProvided = instance;

        for (Field field : ReflectionUtils.getFieldsAnnotated(GetValue.class, instance.getClass().getPackage().getName())) {
            field.setAccessible(true);
            field.set(listOfFiles.get(field.getAnnotation(GetValue.class).file()), listOfFiles.get(field.getAnnotation(GetValue.class).file()).get().get(field.getAnnotation(GetValue.class).path()));
        }
    }
}
