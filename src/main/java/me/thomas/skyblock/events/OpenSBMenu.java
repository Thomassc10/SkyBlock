package me.thomas.skyblock.events;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.menus.SbMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenSBMenu implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().isSimilar(SkyBlock.menuItem()))
            SbMenu.openInventory(player);
    }

}
