package us.thezircon.play.gurayaitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.util.ArrayList;
import java.util.List;

public class Tracker {

    private ItemStack tracker;
    private Location targetLoc;
    private Player target;

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public Tracker(Player target) {
        this.target = target;
        this.targetLoc = target.getLocation();
    }

    public Tracker() {

    }

    public ItemStack getTracker() {
        String targetName;
        if (target!=null) {
            targetName = target.getName();
        } else {
            targetName = "None";
        }
        ItemStack tracker = new ItemStack(Material.COMPASS);
        ItemMeta meta = tracker.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Tracker.Name")).replace("{target}", targetName));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("Tracker.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string.replace("{target}", targetName)));
        }
        meta.setLore(lore);

        tracker.setItemMeta(meta);
        this.tracker = tracker;
        return tracker;
    }

}
