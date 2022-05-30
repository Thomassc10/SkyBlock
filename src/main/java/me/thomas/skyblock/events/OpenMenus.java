package me.thomas.skyblock.events;

import me.thomas.skyblock.menus.CraftingMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OpenMenus implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getView().getTitle().contains("SkyBlock Menu")) return;
        if (event.getCurrentItem() == null) return;
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        switch (event.getSlot()){
            case 25:
                player.sendMessage("");
                break;
            case 31:
                CraftingMenu.openInventory(player);
                break;
            default:
                break;
        }
    }
}
