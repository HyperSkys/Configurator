/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.utils;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Some awesome cool ways to get all fields annotated with a value.
 * @since 1.0.7-STABLE
 */
public class ReflectionUtils {
    public static Set<Field> getFieldsAnnotated(Class<? extends Annotation> annotation, String packageName) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(packageName))
                        .setScanners(new FieldAnnotationsScanner())
                        )
        ;
        return reflections.getFieldsAnnotatedWith(annotation);
    }

    public static void disableReflectionsLogger() {
        System.setProperty("org.slf4j.simpleLogger.log.org.reflections", "off");
    }
}