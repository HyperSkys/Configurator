package dev.hyperskys.configurator.api.exception;

public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(String path) {
        super(path);
    }
}
