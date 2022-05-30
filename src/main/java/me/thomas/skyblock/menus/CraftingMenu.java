package me.thomas.skyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CraftingMenu {

    private static Inventory INV;

    public static void register(){
        Inventory inv = Bukkit.createInventory(null, 54, "Crafting Menu");
        for (int i = 0; i < 54; i++){
            if (i == 10 || i == 11 || i == 12 || i == 19 || i == 20 || i == 21 || i == 28 || i == 29 || i == 30)
                continue;
            inv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        inv.setItem(23, new ItemStack(Material.BARRIER));
        // 10 11 12, 19 20 21, 28 29 30
        setInventory(inv);
    }

    public Inventory getInventory() {
        return INV;
    }

    public static void setInventory(Inventory inv) {
        CraftingMenu.INV = inv;
    }

    public static void openInventory(Player player){
        player.openInventory(INV);
    }
}
