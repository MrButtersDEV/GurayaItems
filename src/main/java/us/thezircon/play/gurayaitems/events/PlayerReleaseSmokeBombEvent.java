package us.thezircon.play.gurayaitems.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class PlayerReleaseSmokeBombEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player executor;
    ArrayList<Player> affected;

    public PlayerReleaseSmokeBombEvent(Player executor, ArrayList<Player> affected){
        this.executor = executor;
        this.affected = affected;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getExecutor() {
        return executor;
    }

    public ArrayList<Player> getAffected() {
        return affected;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }
}