package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.events.PlayerShootMinigunEvent;

import java.util.HashMap;

public class PlayerShootListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    private HashMap<Player, Boolean> wave = new HashMap<>();

    @EventHandler
    public void onShoot(PlayerShootMinigunEvent e) {
        Player player = e.getPlayer();
        Location loc = player.getLocation();

        if (plugin.cooldownMinigun.containsKey(player)) {
            int cooldown = plugin.getConfig().getInt("BriefcaseMinigun.Cooldown");
            long secondsLeft = (plugin.cooldownMinigun.get(player)/1000)+cooldown - (System.currentTimeMillis()/1000);
            if (secondsLeft>0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.onCooldown").replace("{timeleft}", secondsLeft + "")));
                return;
            }
        }

        if (!wave.containsKey(player)) {
            wave.put(player, true);
            int Usage = plugin.getConfig().getInt("BriefcaseMinigun.Usage");
            new BukkitRunnable() {
                @Override
                public void run() {
                    //Cooldown
                    long time = System.currentTimeMillis();
                    plugin.cooldownMinigun.put(player, time);
                    wave.remove(player);
                }
            }.runTaskLater(plugin, Usage*20);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Sound sound = Sound.valueOf(plugin.getConfig().getString("BriefcaseMinigun.SoundEffect.Sound"));
                double vol = plugin.getConfig().getDouble("BriefcaseMinigun.SoundEffect.Volume");
                double pitch = plugin.getConfig().getDouble("BriefcaseMinigun.SoundEffect.Pitch");
                loc.getWorld().playSound(loc, sound, (float) vol, (float) pitch);

                Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
                snowball.setShooter(player);
                snowball.setCustomName("bullet");
                snowball.setVelocity(player.getLocation().getDirection().multiply(plugin.getConfig().getDouble("BriefcaseMinigun.BulletVelocity")));
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void onShot(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && e.getDamager().getName().equals("bullet")) {
            e.setDamage(plugin.getConfig().getDouble("BriefcaseMinigun.BulletDamage"));
        }
    }

}
