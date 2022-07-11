/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.api.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String path) {
        super(path);
    }
}
