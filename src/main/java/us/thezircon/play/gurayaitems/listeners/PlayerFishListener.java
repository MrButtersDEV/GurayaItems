package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.gurayaitems.events.PlayerGrappleEvent;
import us.thezircon.play.gurayaitems.items.GrapplingHook;

public class PlayerFishListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        ItemMeta handMeta = hand.getItemMeta();
        ItemMeta grappleMeta = GrapplingHook.getGrapple().getItemMeta();

        try {
            if (handMeta.getDisplayName().equals(grappleMeta.getDisplayName()) && handMeta.getLore().equals(grappleMeta.getLore())) {
                if (e.getState().equals(PlayerFishEvent.State.REEL_IN) || e.getState().equals(PlayerFishEvent.State.IN_GROUND)) {
                    Bukkit.getServer().getPluginManager().callEvent(new PlayerGrappleEvent(player, e.getHook().getLocation()));
                }
            }
        } catch (NullPointerException ignored) {}
    }
}