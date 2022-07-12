/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.events;

import dev.hyperskys.configurator.api.Configuration;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This event will fire if any configuration file is reloaded.
 * @since 1.0.0-RELEASE
 */
public class ConfigurationReloadEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private static Configuration configurationFile = null;
    private static boolean isCancelled;

    /**
     * The constructor that sets up the event, only needed for firing the event.
     * @param configuration An instance of the Configuration file
     */
    public ConfigurationReloadEvent(Configuration configuration) {
        configurationFile = configuration;
    }

    /**
     * Grabs the amount of time it took to reload the file.
     * @return The amount of time it took the file to reload in milliseconds (ex: 52ms).
     */
    public long getReloadTimeMS() {
        return configurationFile.getReloadTime();
    }

    /**
     * Allows you to grab the name of the file that was reloaded, for logging purpose.
     * @return The name of the file that was reloaded.
     */
    public String getFileReloaded() {
        return configurationFile.getNameFile();
    }

    /**
     * Allows you to grab the instance of the Configuration file reloaded.
     * @return An instance of the Configuration object.
     */
    public Configuration getInstance() {
        return configurationFile;
    }

    /**
     * I have no idea why'd you need to use this method, but whatever.
     * @return A list of handlers that the event has.
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Allows you to check if the event was cancelled or not for handling purposes.
     * @return A boolean saying if the event was cancelled or not.
     */
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Allows you to cancel the event if you want to, maybe needed for something.
     * @param bool Set the cancellation status of the event.
     */
    @Override
    public void setCancelled(boolean bool) {
        isCancelled = bool;
    }
}
