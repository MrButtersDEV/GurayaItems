package us.thezircon.play.gurayaitems.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.thezircon.play.gurayaitems.GurayaItems;
import us.thezircon.play.gurayaitems.items.*;

import java.util.Arrays;
import java.util.List;

public class GiveItem implements TabExecutor {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("gurayaitems.use")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.msgNoPerm")));
            return false;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length>0) {
                if (args[0].equalsIgnoreCase("RiggedPen")) {
                    player.getInventory().addItem(RiggedPen.getPen());
                } else if (args[0].equalsIgnoreCase("SmokeBomb")) {
                    ItemStack i = SmokeBomb.getBomb();
                    if (args[1]!=null) {
                        i.setAmount(Integer.parseInt(args[1]));
                    }
                    player.getInventory().addItem(i);
                } else if (args[0].equalsIgnoreCase("Tracker")) {
                    Tracker tracker = new Tracker();
                    player.getInventory().addItem(tracker.getTracker());
                } else if (args[0].equalsIgnoreCase("GrapplingHook")) {
                    player.getInventory().addItem(GrapplingHook.getGrapple());
                } else if (args[0].equalsIgnoreCase("XRayGoggles")) {
                    //player.getInventory().addItem(XRayGoggles.getGoggles());
                    player.sendMessage(ChatColor.YELLOW + "This item has yet to be implemented!");
                } else if (args[0].equalsIgnoreCase("BriefcaseMinigun")) {
                    player.getInventory().addItem(BriefcaseMinigun.getMinigun());
                }
            }

        } else {
            if (args.length>0) {
                try {
                    if (args.length>=2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (args[1].equalsIgnoreCase("RiggedPen")) {
                            target.getInventory().addItem(RiggedPen.getPen());
                        } else if (args[1].equalsIgnoreCase("SmokeBomb")) {
                            ItemStack i = SmokeBomb.getBomb();
                            if (args.length==3) {
                                i.setAmount(Integer.parseInt(args[2]));
                            }
                            target.getInventory().addItem(i);
                        } else if (args[1].equalsIgnoreCase("Tracker")) {
                            Tracker tracker = new Tracker();
                            target.getInventory().addItem(tracker.getTracker());
                        } else if (args[1].equalsIgnoreCase("GrapplingHook")) {
                            target.getInventory().addItem(GrapplingHook.getGrapple());
                        } else if (args[1].equalsIgnoreCase("XRayGoggles")) {
                            //player.getInventory().addItem(XRayGoggles.getGoggles());
                            sender.sendMessage(ChatColor.YELLOW + "This item has yet to be implemented!");
                        } else if (args[1].equalsIgnoreCase("BriefcaseMinigun")) {
                            target.getInventory().addItem(BriefcaseMinigun.getMinigun());
                        }
                    } else {
                        sender.sendMessage("Error: /gi [player] [item] <amt>");
                    }
                } catch (Error err) {
                    sender.sendMessage("Error: /gi [player] [item] <amt>");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length>0) {
            if (args.length==1) {
                return Arrays.asList("RiggedPen", "SmokeBomb", "Tracker", "GrapplingHook", "XRayGoggles", "BriefcaseMinigun");
            }
        }
        return null;
    }
}
