package us.thezircon.play.gurayaitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.util.ArrayList;
import java.util.List;

public class GrapplingHook {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public static ItemStack getGrapple() {
        ItemStack grapplingHook = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = grapplingHook.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("GrapplingHook.Name")));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("GrapplingHook.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(lore);
        if (plugin.getConfig().getBoolean("GrapplingHook.Unbreakable")) {
            meta.setUnbreakable(true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        grapplingHook.setItemMeta(meta);
        return grapplingHook;
    }

}
