
package github.minecraftdevlearning.src.spigotplugin;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class ExampleEvent extends Event {
    private final String playerName;
    private boolean isCancelled;

    public ExampleEvent(String playerName) {
        this.playerName = playerName;
        this.isCancelled = false;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}