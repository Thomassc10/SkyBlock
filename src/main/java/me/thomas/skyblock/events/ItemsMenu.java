package me.thomas.skyblock.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemsMenu implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getView().getTitle().contains("SkyBlock Items")) return;
        if (event.getCurrentItem() == null) return;
        Player player = (Player) event.getWhoClicked();
        player.getInventory().addItem(event.getCurrentItem());
        event.setCancelled(true);
    }
}
