package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.events.PlayerToggleRiggedPenEvent;
import us.thezircon.play.gurayaitems.items.RiggedPen;


import java.util.HashMap;

public class TogglePenListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    private HashMap<Player, Boolean> wave = new HashMap<>();
    private HashMap<Player, Long> toggleLimiter = new HashMap<>();

    @EventHandler
    public void onToggle(PlayerToggleRiggedPenEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = e.getItem();
        Location loc = player.getLocation();

        if (toggleLimiter.containsKey(player)) {
            long secondsLeft = (toggleLimiter.get(player)/1000)+1 - (System.currentTimeMillis()/1000);
            if (secondsLeft>0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.onCooldown").replace("{timeleft}", secondsLeft + "")));
                return;
            }
        }

        toggleLimiter.put(player, System.currentTimeMillis());

        if (hand.equals(RiggedPen.getPen())){

            if (plugin.cooldownRiggedPen.containsKey(player)) {
                int cooldown = plugin.getConfig().getInt("RiggedPen.Cooldown");
                long secondsLeft = (plugin.cooldownRiggedPen.get(player)/1000)+cooldown - (System.currentTimeMillis()/1000);
                if (secondsLeft>0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.onCooldown").replace("{timeleft}", secondsLeft + "")));
                    return;
                }
            }

            if (!wave.containsKey(player)) {
                wave.put(player, true);
                int Usage = plugin.getConfig().getInt("RiggedPen.Usage");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        //Cooldown
                        long time = System.currentTimeMillis();
                        plugin.cooldownRiggedPen.put(player, time);
                        wave.remove(player);
                        if (player.getInventory().contains(RiggedPen.getSword())) {
                            for (ItemStack items : player.getInventory()) {
                                if (items != null && items.isSimilar(RiggedPen.getSword())) {
                                    player.getInventory().setItem(player.getInventory().first(RiggedPen.getSword()), RiggedPen.getPen());
                                }
                            }
                        }
                    }
                }.runTaskLater(plugin, Usage*20);
            }

            // Enable Sword
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getInventory().setItemInMainHand(RiggedPen.getSword());
                    player.updateInventory();
                    playSound(loc);
                }
            }.runTaskLater(plugin, 0);
        } else if (hand.equals(RiggedPen.getSword())) {
            // Enable Pen
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getInventory().setItemInMainHand(RiggedPen.getPen());
                    player.updateInventory();
                    playSound(loc);
                }
            }.runTaskLater(plugin, 0);
        }
    }

    private void playSound(Location loc) {
        Sound sound = Sound.valueOf(plugin.getConfig().getString("RiggedPen.SoundEffect.Sound"));
        double vol = plugin.getConfig().getDouble("RiggedPen.SoundEffect.Volume");
        double pitch = plugin.getConfig().getDouble("RiggedPen.SoundEffect.Pitch");
        loc.getWorld().playSound(loc, sound, (float) vol, (float) pitch);
    }

}