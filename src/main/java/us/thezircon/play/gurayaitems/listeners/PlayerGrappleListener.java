package us.thezircon.play.gurayaitems.listeners;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.events.PlayerGrappleEvent;
import us.thezircon.play.gurayaitems.items.GrapplingHook;

import java.util.HashMap;

public class PlayerGrappleListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);
    public HashMap<Player, Boolean> doFallDamage = new HashMap<>();

    @EventHandler
    public void onGrapple(PlayerGrappleEvent e) {
        Player player = e.getPlayer();

        if (plugin.cooldownGrapplingHook.containsKey(player)) {
            int cooldown = plugin.getConfig().getInt("GrapplingHook.Cooldown");
            long secondsLeft = (plugin.cooldownGrapplingHook.get(player)/1000)+cooldown - (System.currentTimeMillis()/1000);
            if (secondsLeft>0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.onCooldown").replace("{timeleft}", secondsLeft + "")));
                return;
            }
        }

        Location loc = e.getTarget();
        Location ploc = player.getLocation();
        loc.subtract(ploc);
        loc.multiply(0.5);

        //Caps Vertical Velocity
        if (loc.getY()>2) {
            loc.setY(2);
        }

        player.setVelocity(loc.toVector());
        doFallDamage.put(player, false);

        //Cooldown
        long time = System.currentTimeMillis();
        plugin.cooldownGrapplingHook.put(player, time);
    }

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (doFallDamage.containsKey(player)) {
                if (doFallDamage.get(player).equals(false) && player.getInventory().getItemInMainHand().equals(GrapplingHook.getGrapple())) {
                    e.setCancelled(true);
                    doFallDamage.remove(player);
                }
            }
        }
    }

}
