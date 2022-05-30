package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PrismarineBlade extends SbItem implements Listener {

    public PrismarineBlade() {
        super(new ItemStack(Material.PRISMARINE), "Prismarine Blade", 50, 25, Arrays.asList("Deals &a+200% &7damage while in", "&7water."), null, true, SbRarity.UNCOMMON_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (Utils.isRightItem(event, getItem())) return;
        if (event.getDamager().isInWater())
            event.setDamage(event.getDamage() * 3);
    }
}
