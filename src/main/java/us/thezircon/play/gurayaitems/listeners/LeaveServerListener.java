package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.items.Tracker;

public class LeaveServerListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        //Reset a players tracking data
        if (plugin.trackingInfo.containsKey(player)) {
            Tracker tracker = new Tracker();
            Tracker trackingTracker = new Tracker(plugin.trackingInfo.get(player));
            player.setCompassTarget(player.getWorld().getSpawnLocation());
            int slot = player.getInventory().first(trackingTracker.getTracker());
            player.getInventory().setItem(slot, tracker.getTracker());
            plugin.trackingInfo.remove(player);
        }

        if (plugin.glowingInfo.containsKey(player)) {
            plugin.glowingInfo.remove(player);
        }
    }

}
