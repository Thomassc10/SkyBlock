package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class GiantCleaver extends me.thomas.skyblock.items.SbItem implements Listener {

    public GiantCleaver() {
        super(new ItemStack(Material.GOLDEN_SWORD), "Giant Cleaver", 170, 60, null, Collections.singletonList(new SbAbility("Cleave",
                AbilityType.NONE, Arrays.asList("&7When hitting an entity, monsters", "&7in a &a5 &7block range will be",
                "&7hit for a portion of that damage", "&7too."))), true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (Utils.isRightItem(event, getItem())) return;

        double damage = event.getDamage() / 2;
        for (LivingEntity lv : Utils.getNearestEntities(event.getEntity(), 5)) {
            lv.damage(damage);
        }
    }
}
