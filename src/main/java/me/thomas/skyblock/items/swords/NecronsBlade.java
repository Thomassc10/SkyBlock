package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class NecronsBlade extends me.thomas.skyblock.items.SbItem implements Listener {

    public NecronsBlade() {
        super(new ItemStack(Material.IRON_SWORD), "Necron's Blade (Unrefined)", 260, 110, 0,
                0, 50, 0, 0, 30, Arrays.asList("&7Deals &c+50% &7damage to",
                        "&7Withers."), null, true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.WITHER)
            event.setDamage(event.getDamage() * 1.5);
    }
}
