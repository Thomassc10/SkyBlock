package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class LeapingSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public LeapingSword() {
        super(new ItemStack(Material.GOLDEN_SWORD), "Leaping Sword", 150, 100, 0, 25, 0, 0, 0, null,
                Collections.singletonList(new SbAbility("Leap", AbilityType.RIGHT_CLICK, Arrays.asList("&7Leap into the air and deal",
                        "&c350 &7damage to nearby enemies", "&7upon landing on the ground.", "&7Damaged enemies will also be",
                        "&7frozen for &a1.0 &7second."), 50, 1)), true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
        Utils.setIntInEntity(player, "leaping", 0);
    }

    @EventHandler
    public void onLand(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getTo().getBlock().getType() != Material.AIR) {
            if (Utils.getIntFromEntity(player, "leaping") == 0) {
                Utils.setIntInEntity(player, "leaping", 1);
                player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 1);
                for (LivingEntity living : Utils.getNearestEntities(player, 2)) {
                    if (!(living instanceof Player))
                        living.damage(350);
                }
            }
        }
    }
}
