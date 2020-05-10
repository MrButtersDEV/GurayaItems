package us.thezircon.play.gurayaitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.util.ArrayList;
import java.util.List;

public class SmokeBomb {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public static ItemStack getBomb(){
        ItemStack smokeBomb = new ItemStack(Material.FLINT);
        ItemMeta meta = smokeBomb.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("SmokeBomb.Name")));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("SmokeBomb.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(lore);

        smokeBomb.setItemMeta(meta);
        return smokeBomb;
    }

}
