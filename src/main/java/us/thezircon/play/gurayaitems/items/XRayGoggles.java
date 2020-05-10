package us.thezircon.play.gurayaitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.util.ArrayList;
import java.util.List;

public class XRayGoggles {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public static ItemStack getGoggles(){
        ItemStack goggles = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) goggles.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("XRayGoggles.Name")));
        List<String> lore = new ArrayList<>();
        for (String string : plugin.getConfig().getStringList("XRayGoggles.Lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(lore);
        meta.setColor(Color.LIME);
        AttributeModifier modifier = new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        goggles.setItemMeta(meta);
        return goggles;
    }

}
