package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.events.PlayerActivateTrackerEvent;
import us.thezircon.play.gurayaitems.events.PlayerReleaseSmokeBombEvent;
import us.thezircon.play.gurayaitems.events.PlayerShootMinigunEvent;
import us.thezircon.play.gurayaitems.events.PlayerToggleRiggedPenEvent;
import us.thezircon.play.gurayaitems.items.BriefcaseMinigun;
import us.thezircon.play.gurayaitems.items.RiggedPen;
import us.thezircon.play.gurayaitems.items.SmokeBomb;
import us.thezircon.play.gurayaitems.items.Tracker;

import java.util.ArrayList;


public class InteractListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        Action a = e.getAction();

        //Blank Tracker
        Tracker blankTracker = new Tracker();
        Tracker trackingTracker = new Tracker();
        if (plugin.trackingInfo.containsKey(player)) {
            trackingTracker = new Tracker(plugin.trackingInfo.get(player));
            plugin.trackingInfo.remove(player);
        }

        //Check if player clicks an item
        if ((a.equals(Action.RIGHT_CLICK_AIR)) || (a.equals(Action.RIGHT_CLICK_BLOCK))) {
            if (hand.equals(RiggedPen.getPen()) || hand.equals(RiggedPen.getSword()) && a!=(Action.RIGHT_CLICK_BLOCK)) { // Rigged Pen
                Bukkit.getServer().getPluginManager().callEvent(new PlayerToggleRiggedPenEvent(player, hand)); // Call Toggle event!
            } else if (hand.isSimilar(SmokeBomb.getBomb())) { // Smoke Bomb
                ArrayList<Player> nearbyePlayers = new ArrayList<>();
                for (Entity near : player.getNearbyEntities(5, 5, 5)) {
                    if (near instanceof Player) {
                        nearbyePlayers.add((Player) near);
                    }
                }
                Bukkit.getServer().getPluginManager().callEvent(new PlayerReleaseSmokeBombEvent(player, nearbyePlayers));
            } else if (hand.isSimilar(blankTracker.getTracker()) || hand.isSimilar(trackingTracker.getTracker())) {
                Player target = null;
                for (Entity near : player.getNearbyEntities(100, 100, 100)) {
                    if (near instanceof Player) {
                        target = (Player) near;
                    }
                }
                Bukkit.getServer().getPluginManager().callEvent(new PlayerActivateTrackerEvent(player, target));
            } else if (hand.isSimilar(BriefcaseMinigun.getMinigun())) {
                e.setCancelled(true);
                Bukkit.getServer().getPluginManager().callEvent(new PlayerShootMinigunEvent(player, hand));
            }
        }
    }
}