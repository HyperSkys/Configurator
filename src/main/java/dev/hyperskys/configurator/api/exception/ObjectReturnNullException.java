package dev.hyperskys.configurator.api.exception;

/**
 * Will fire if a default was not provided, and config value provided returned null.
 * @since 2.0.0-RELEASE
 */
public class ObjectReturnNullException extends NullPointerException {
    /**
     * The constructor for the exception that will throw.
     * @param pathOfValue The path of the configuration value (ex. Settings.prefix)
     */
    public ObjectReturnNullException(String pathOfValue) {
        super (pathOfValue);
    }
}
