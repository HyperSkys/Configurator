package dev.hyperskys.configurator.utils;

import org.reflections.Reflections;
import java.util.Set;

public class ReflectionUtils {
    public static Set<Class<?>> getClasses(String packageDirectory) {
        return new Reflections(packageDirectory).getSubTypesOf(Object.class);
    }
}
