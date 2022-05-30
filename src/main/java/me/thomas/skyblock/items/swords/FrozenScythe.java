package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;

public class FrozenScythe extends me.thomas.skyblock.items.SbItem implements Listener {

    public FrozenScythe() {
        super(new ItemStack(Material.IRON_HOE), "Frozen Scythe", 80, 0, null, Collections.singletonList(
                new SbAbility("Ice Bolt", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots &a1 &7Ice Bolt that deals",
                        "&c1,000 &7damage and slows", "&7enemies hit for &a5 &7seconds!"), 50)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Utils.isRightItem(event.getPlayer(), getItem())) return;
        Player player = event.getPlayer();
        if (Utils.isRightClick(event.getAction())) {
            Arrow arrow = player.launchProjectile(Arrow.class);
            arrow.setVelocity(player.getLocation().getDirection().multiply(3));
            Utils.setStringInEntity(arrow, "frozen", "frozen");
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ARROW) {
            Entity entity = event.getEntity();
            if (Utils.getStringFromEntity(entity, "frozen").equals("frozen")) {
                if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {
                    ((LivingEntity) event.getHitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2));
                    ((LivingEntity) event.getHitEntity()).damage(1000);
                }
            }
        }
    }
}
