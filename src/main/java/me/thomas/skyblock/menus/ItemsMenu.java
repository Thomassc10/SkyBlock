package me.thomas.skyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsMenu {

    private static Inventory INV;

    public static void register() {
        Inventory inv = Bukkit.createInventory(null, 9, "SkyBlock Items");
        /*for (SbItem item : Items.getSbItems().values()) {
            if (item != null)
                inv.addItem(item.getItem());
        }
        for (SbArmor armor : Items.getSbArmors().values()) {
            if (armor != null)
                inv.addItem(armor.getItem());
        }*/
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Swords");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inv.setItem(0, item);

        item.setType(Material.BOW);
        meta.setDisplayName(ChatColor.GREEN + "Bows");
        item.setItemMeta(meta);
        inv.setItem(1, item);

        item.setType(Material.LEATHER_CHESTPLATE);
        meta.setDisplayName(ChatColor.GREEN + "Armor Sets");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        item.setType(Material.BEACON);
        meta.setDisplayName("Miscellaneous");
        item.setItemMeta(meta);
        inv.setItem(3, item);


        setInventory(inv);
    }

    public Inventory getInventory(){
        return INV;
    }

    public static void setInventory(Inventory inv){
        INV = inv;
    }

    public static void openInventory(Player player){
        player.openInventory(INV);
    }
}
