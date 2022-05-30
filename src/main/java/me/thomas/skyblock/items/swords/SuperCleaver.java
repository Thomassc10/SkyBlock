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

public class SuperCleaver extends me.thomas.skyblock.items.SbItem implements Listener {

    public SuperCleaver() {
        super(new ItemStack(Material.GOLDEN_SWORD), "Super Cleaver", 85, 20, null, Collections.singletonList(new SbAbility("Cleave",
                AbilityType.NONE, Arrays.asList("&7When hitting an entity, monsters", "&7in a &a3 &7block range will be",
                "&7hit for a portion of that damage", "&7too."))), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (Utils.isRightItem(event, getItem())) return;

        double damage = event.getDamage() / 3;
        for (LivingEntity lv : Utils.getNearestEntities(event.getEntity(), 3)) {
            lv.damage(damage);
        }
    }
}
