package me.thomas.skyblock.commands;

import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbArmor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
            try {
                if (Items.getSbItems().containsKey(args[0].toLowerCase()))
                    player.getInventory().addItem(Items.getSbItemByKey(args[0]).getItem());
                else if (Items.getSbArmors().containsKey(args[0].toLowerCase())) {
                    player.getInventory().addItem(Items.getSbArmorByKey(args[0]).getItem());
                } else {
                    for (SbArmor item : Items.getArmorSetByKey(args[0]))
                        player.getInventory().addItem(item.getItem());
                }
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }
}
