package me.thomas.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SbMenu implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("sbmenu")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Cannot do this!");
                return true;
            }
            Player player = (Player) sender;
            me.thomas.skyblock.menus.SbMenu.openInventory(player);
            return true;
        }
        return false;
    }
}
