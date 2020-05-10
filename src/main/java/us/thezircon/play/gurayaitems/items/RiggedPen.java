package us.thezircon.play.gurayaitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.util.ArrayList;
import java.util.List;

public class RiggedPen {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public static ItemStack getPen(){
        ItemStack RiggedPen = new ItemStack(Material.STICK);
        ItemMeta meta = RiggedPen.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RiggedPen.Stage1.Name")));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("RiggedPen.Stage1.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(lore);

        RiggedPen.setItemMeta(meta);
        return RiggedPen;
    }

    public static ItemStack getSword(){
        ItemStack PenSword = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = PenSword.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RiggedPen.Stage2.Name")));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("RiggedPen.Stage2.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, plugin.getConfig().getInt("RiggedPen.Stage2.Sharpness"), true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        PenSword.setItemMeta(meta);
        return PenSword;
    }

}
