package dev.hyperskys.configurator.api.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String path) {
        super(path);
    }
}
