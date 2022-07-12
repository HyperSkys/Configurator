package dev.hyperskys.configurator.api.exception;

/**
 * Will fire if an object does not exist with provided value.
 * @since 2.0.0-RELEASE
 */
public class ObjectNotExistentException extends RuntimeException {
    /**
     * The constructor for the exception that will throw.
     * @param fileName The name of the file (ex. config.yml)
     */
    public ObjectNotExistentException(String fileName) {
        super(fileName);
    }
}
