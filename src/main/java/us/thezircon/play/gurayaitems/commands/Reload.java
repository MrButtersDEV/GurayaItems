package us.thezircon.play.gurayaitems.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.thezircon.play.gurayaitems.GurayaItems;

public class Reload implements CommandExecutor {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("gurayaitems.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.msgReload")));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.msgNoPerm")));
        }
        return false;
    }
}
