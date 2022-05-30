package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SoulWhip extends me.thomas.skyblock.items.SbItem implements Listener {

    public SoulWhip() {
        super(new ItemStack(Material.FISHING_ROD), "Sould Whip", 145, 175, null, Collections.singletonList(
                new SbAbility("Flay", AbilityType.RIGHT_CLICK, Arrays.asList("&7Flay your whip in an arc,",
                        "&7dealing your melee damage to all", "&7enemies in its path."))), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection();
        for (double i = 0; i < 50; i+=0.1) {
            dir.multiply((-i*i + 6*i)/6);
            loc.clone().add(dir);
            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 1);
            List<Entity> entities = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 0.1, 0.1, 0.1);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity && !(entity instanceof Player))
                    ((LivingEntity)entity).damage(10);
            }
            dir.normalize();
        }
    }
}






