package us.thezircon.play.gurayaitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.util.ArrayList;
import java.util.List;

public class BriefcaseMinigun {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public static ItemStack getMinigun(){
        ItemStack minigun = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = minigun.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("BriefcaseMinigun.Name")));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("BriefcaseMinigun.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        minigun.setItemMeta(meta);
        return minigun;
    }

}
