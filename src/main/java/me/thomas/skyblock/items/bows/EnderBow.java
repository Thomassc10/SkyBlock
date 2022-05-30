package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class EnderBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public EnderBow() {
        super(new ItemStack(Material.BOW), "Ender Bow", 60, 0, null, Collections.singletonList(
                new SbAbility("Ender Warp", AbilityType.LEFT_CLICK, Arrays.asList("&7Shoots and Ender Pearl. Upon",
                        "&7landing you deal damage to all Monsters in a &a8.0 &7block",
                        "&7radius for &a10.0% &7of their", "&c%health%Health&7."), 50, 45)), true, SbRarity.RARE_BOW);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        if (event.getType() == AbilityType.LEFT_CLICK) {
            EnderPearl pearl = player.launchProjectile(EnderPearl.class);
            pearl.setVelocity(player.getLocation().getDirection().multiply(1));
            Utils.setIntInEntity(pearl, "warp", 1);
        }
    }

    @EventHandler
    public void onLand(ProjectileHitEvent event) {
        if (event.getEntity().getType() != EntityType.ENDER_PEARL) return;
        if (Utils.getIntFromEntity(event.getEntity(), "warp") == 1) {
            for (LivingEntity e : Utils.getNearestEntities(event.getEntity(), 8)) {
                e.damage(Utils.getPercent(e.getHealth(), 10));
            }
        }
    }
}
