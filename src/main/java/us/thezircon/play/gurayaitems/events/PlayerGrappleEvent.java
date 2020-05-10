package us.thezircon.play.gurayaitems.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerGrappleEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;
    Location target;

    public PlayerGrappleEvent(Player player, Location target){
        this.player = player;
        this.target = target;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getTarget() {
        return target;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
