package us.thezircon.play.gurayaitems.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerActivateTrackerEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player executor;
    Player target;

    public PlayerActivateTrackerEvent(Player executor, Player target){
        this.executor = executor;
        this.target = target;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getExecutor() {
        return executor;
    }

    public Player getTarget() {
        return target;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
