package me.thomas.skyblock.events;

import me.thomas.skyblock.helpers.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SbItemPlace implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlace(BlockPlaceEvent event) {
        if (Utils.isSbItem(event.getPlayer().getInventory().getItemInMainHand()))
            event.setCancelled(true);
    }
}
