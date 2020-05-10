package us.thezircon.play.gurayaitems.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerShootMinigunEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;
    ItemStack gun;

    public PlayerShootMinigunEvent(Player player, ItemStack gun){
        this.player = player;
        this.gun = gun;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getGun() {
        return gun;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
