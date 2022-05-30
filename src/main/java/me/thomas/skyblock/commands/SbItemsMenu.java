package me.thomas.skyblock.commands;

import me.thomas.skyblock.menus.ItemsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SbItemsMenu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("items")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            ItemsMenu.openInventory(player);
            return true;
        }
        return false;
    }
}
