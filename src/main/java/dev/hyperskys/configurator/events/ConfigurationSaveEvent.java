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

    public long getSaveTimeMS() {
        return configurationFile.getReloadTime();
    }

    public String getFileSaved() {
        return configurationFile.getNameFile();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
