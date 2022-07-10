/**
 * @author HyperSkys Development
 * Copyright (C) 2020-2022 - HyperSkys Development
 */

package dev.hyperskys.configurator.events;

import dev.hyperskys.configurator.api.Configuration;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ConfigurationReloadEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private static Configuration configurationFile = null;
    private static boolean isCancelled;

    public ConfigurationReloadEvent(Configuration configuration) {
        configurationFile = configuration;
    }

    public long getReloadTimeMS() {
        return configurationFile.getReloadTime();
    }

    public String getFileReloaded() {
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
