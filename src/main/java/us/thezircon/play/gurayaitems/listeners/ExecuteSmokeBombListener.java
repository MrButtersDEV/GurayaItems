package us.thezircon.play.gurayaitems.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.events.PlayerReleaseSmokeBombEvent;
import us.thezircon.play.gurayaitems.items.SmokeBomb;


public class ExecuteSmokeBombListener implements Listener {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    @EventHandler
    public void onExecute(PlayerReleaseSmokeBombEvent e) {
        Player player = e.getExecutor();
        ItemStack hand = player.getInventory().getItemInMainHand();
        Location loc = player.getLocation();
        /*

        Add Cooldown - Runnable to disable and timestamp math to enable

         */

        if (plugin.cooldownSmokeBomb.containsKey(player)) {
            int cooldown = plugin.getConfig().getInt("SmokeBomb.Cooldown");
            long secondsLeft = (plugin.cooldownSmokeBomb.get(player)/1000)+cooldown - (System.currentTimeMillis()/1000);
            if (secondsLeft>0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.onCooldown").replace("{timeleft}", secondsLeft + "")));
                return;
            }
        }

        if (hand.isSimilar(SmokeBomb.getBomb())) {
            //Reduce Item amt
            int newAmt = hand.getAmount()-1;
            ItemStack newStack = SmokeBomb.getBomb();
            newStack.setAmount(newAmt);
            player.getInventory().setItemInMainHand(newStack);
            player.updateInventory();

            Sound sound = Sound.valueOf(plugin.getConfig().getString("SmokeBomb.SoundEffect.Sound"));
            double vol = plugin.getConfig().getDouble("SmokeBomb.SoundEffect.Volume");
            double pitch = plugin.getConfig().getDouble("SmokeBomb.SoundEffect.Pitch");
            loc.getWorld().playSound(loc, sound, (float) vol, (float) pitch);

            //Give Nearby players blindness
            for (Player players : e.getAffected()) {
                players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, plugin.getConfig().getInt("SmokeBomb.Blindness")*20, 1));
            }

            //Send Particles
            loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc.getX(), loc.getY(), loc.getZ(), plugin.getConfig().getInt("SmokeBomb.ParticleAmt"));

            //Cooldown
            long time = System.currentTimeMillis();
            plugin.cooldownSmokeBomb.put(player, time);

        }
    }

}
