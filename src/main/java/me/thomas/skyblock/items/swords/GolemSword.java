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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class GolemSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public GolemSword() {
        super(new ItemStack(Material.IRON_SWORD), "Golem Sword", 80, 125, 0, 0, 0, 0, 25, null, Collections.singletonList(
                new SbAbility("Iron Punch", AbilityType.RIGHT_CLICK, Arrays.asList("&Punch the ground, damaging",
                        "&7enemies in a hexagon around you", "&7for &a250 &7base Magic Damage"), 70, 3)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        for (LivingEntity lv : Utils.getNearestEntities(player, 2)) {
            lv.damage(250);
        }
        Utils.spawnCircle(3, player, Particle.EXPLOSION_NORMAL);
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
    }
}
