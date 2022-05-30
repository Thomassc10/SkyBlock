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

public class RevenantFalchion extends me.thomas.skyblock.items.SbItem implements Listener {

    public RevenantFalchion() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Revenant Falchion", 90, 50, 0, 0, 100,
                0, 0, Arrays.asList("&7Deals &a+150% &7damage to", "&7Zombies."), null, true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem()));
        if (event.getEntityType() == EntityType.ZOMBIE)
            event.setDamage(event.getDamage() * 2.5);
    }
}
