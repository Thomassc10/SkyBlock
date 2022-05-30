package me.thomas.skyblock.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class OpenItemsInventories implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory().getType() == InventoryType.PLAYER) return;
        if (!event.getView().getTitle().contains("SkyBlock Items")) return;
        if (event.getCurrentItem() == null) return;
        Player player = (Player) event.getWhoClicked();
        switch (event.getSlot()) {
            case 0: {
                Inventory inv = Bukkit.createInventory(null, 45, "Swords");


                player.openInventory(inv);
            }
            break;
            case 1: {
                Inventory inv = Bukkit.createInventory(null, 36, "Bows");

                player.openInventory(inv);
            }
            break;
            case 2: {
                Inventory inv = Bukkit.createInventory(null, 45, "Armor Sets");

                player.openInventory(inv);
            }
            break;
            case 3: {
                Inventory inv = Bukkit.createInventory(null, 36, "Miscellaneous");

                player.openInventory(inv);
            }
            break;
        }
    }
}
