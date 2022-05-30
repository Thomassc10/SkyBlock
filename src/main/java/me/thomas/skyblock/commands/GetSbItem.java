package me.thomas.skyblock.commands;

import me.thomas.skyblock.items.Items;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetSbItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("sbitem")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Usage: /sbitem <item>");
                return true;
            }
            player.getInventory().addItem(Items.getSbItemByKey(args[0]).getItem());
            return true;
        }
        return false;
    }
}
