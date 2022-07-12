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
 * This event will fire if any configuration file is saved.
 * @since 1.0.5-BETA
 */
public class ConfigurationSaveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private static Configuration configurationFile = null;
    private static boolean isCancelled;

    public ConfigurationSaveEvent(Configuration configuration) {
        configurationFile = configuration;
    }

    /**
     * Grabs the amount of time it took to save the file.
     * @return The amount of time it took the file to save in milliseconds (ex: 52ms).
     */
    public long getSaveTimeMS() {
        return configurationFile.getReloadTime();
    }

    /**
     * Allows you to grab the name of the file that was saved, for logging purpose.
     * @return The name of the file that was saved.
     */
    public String getFileSaved() {
        return configurationFile.getNameFile();
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
     * Bukkit needs this because it likes men.
     * @return The handlers list
     */
    public static HandlerList getHandlerList() {
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
