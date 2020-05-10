package us.thezircon.play.gurayaitems.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerToggleRiggedPenEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;
    ItemStack item;

    public PlayerToggleRiggedPenEvent(Player player, ItemStack item){
        this.player = player;
        this.item = item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
