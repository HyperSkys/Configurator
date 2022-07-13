/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.api.exception;

/**
 * Will fire if the plugin was not found or instance is null.
 * @since 2.0.0-RELEASE
 */
public class PluginNotFoundException extends NullPointerException {
    /**
     * The constructor, yeah it is basic, can't provide information because plugin is null.
     */
    public PluginNotFoundException() {}
}
