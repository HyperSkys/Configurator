package dev.hyperskys.configurator.utils;

import dev.hyperskys.configurator.annotations.GetValue;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;

public class ReflectionUtils {
    public static Set<Field> getFields(String packageDirectory) {
        return new Reflections(packageDirectory).getFieldsAnnotatedWith(GetValue.class);
    }
}
