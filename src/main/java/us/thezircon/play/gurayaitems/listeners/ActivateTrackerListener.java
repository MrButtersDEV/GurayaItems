package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.events.PlayerActivateTrackerEvent;
import us.thezircon.play.gurayaitems.items.Tracker;

public class ActivateTrackerListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    @EventHandler
    public void onTrack(PlayerActivateTrackerEvent e) {
        Player player = e.getExecutor();
        Player target = e.getTarget();
        Location loc = player.getLocation();

        if (target != null) {

            plugin.trackingInfo.put(player, target);

            player.setCompassTarget(target.getLocation());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.msgTracking")).replace("{target}", target.getName()));

            Sound sound = Sound.valueOf(plugin.getConfig().getString("Tracker.SoundEffect.Sound"));
            double vol = plugin.getConfig().getDouble("Tracker.SoundEffect.Volume");
            double pitch = plugin.getConfig().getDouble("Tracker.SoundEffect.Pitch");
            loc.getWorld().playSound(loc, sound, (float) vol, (float) pitch);

            Tracker tracker = new Tracker(target);
            player.getInventory().setItemInMainHand(tracker.getTracker());
            player.updateInventory();
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.msgNoPlayers")));
            player.setCompassTarget(player.getWorld().getSpawnLocation());
            Tracker trackingTracker = new Tracker();
            player.getInventory().setItemInMainHand(trackingTracker.getTracker());
            player.updateInventory();
            plugin.trackingInfo.remove(player);
        }
    }
}
