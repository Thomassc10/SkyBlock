package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class FlamingSword extends SbItem implements Listener {

    public FlamingSword() {
        super(new ItemStack(Material.IRON_SWORD), "Flaming Sword", 50, 20, Collections.singletonList("&7Ignites enemies for &a3s&7."), null, true, SbRarity.UNCOMMON_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        event.getEntity().setFireTicks(60);
    }
}
