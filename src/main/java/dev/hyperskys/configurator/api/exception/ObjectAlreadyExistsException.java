/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.api.exception;

/**
 * Will fire if an object already existed before creation.
 * @since 1.0.5-BETA
 */
public class ObjectAlreadyExistsException extends RuntimeException {
    /**
     * The constructor for the exception that will throw.
     * @param path The path of the configuration value (ex. Settings.prefix)
     */
    public ObjectAlreadyExistsException(String path) {
        super(path);
    }
}
