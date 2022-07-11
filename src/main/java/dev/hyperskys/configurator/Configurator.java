/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator;

import dev.hyperskys.configurator.annotations.GetValue;
import dev.hyperskys.configurator.api.Configuration;
import dev.hyperskys.configurator.utils.ReflectionUtils;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.jar.Attributes;

/**
 * The configurator class for initializing all things to do with Configurator.
 * @version 1.0.8-BETA
 */
public class Configurator {

    private @Getter static Plugin plugin;
    private @Getter static String packageDirectory;
    public static HashMap<String, Configuration> listOfFiles = new HashMap<>();

    /**
     * Set up of the projects global variables that is used for configurator.
     * @param instance An instance of the main plugin.
     * @param packageName The package that the plugin is currently in. (ex. dev.hyperskys.configurator)
     */
    public static void setupConfigurator(Plugin instance, String packageName) {
        plugin = instance;
        packageDirectory = packageName;

        for (Class<?> clazz : ReflectionUtils.findAllClassesUsingClassLoader(packageName)) {
            for (Field field : clazz.getFields()) {
                if (field.isAnnotationPresent(GetValue.class)) {
                    String fileName = field.getAnnotation(GetValue.class).file();
                    String pathName = field.getAnnotation(GetValue.class).path();
                    field.setAccessible(true);
                    try {
                        field.set(listOfFiles.get(fileName), listOfFiles.get(fileName).get().get(pathName));
                    } catch (IllegalAccessException ignored) {}
                }
            }
        }
    }
}
