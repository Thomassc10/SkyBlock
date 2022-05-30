package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class ExplosiveBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public ExplosiveBow() {
        super(new ItemStack(Material.BOW), "Explosive Bow", 100, 20, null, Collections.singletonList(
                new SbAbility("Explosive Shot", AbilityType.NONE, Arrays.asList("&7Creates an explosion on impact!",
                        "&7Every Monster caught in this", "&7explosion takes the full damage", "&7of the weapon!"))), true, SbRarity.EPIC_BOW);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        if (event.getEntity().getType() != EntityType.ARROW) return;
        Player player = (Player) event.getEntity().getShooter();
        Entity entity = event.getEntity();
        if (Utils.getStringFromEntity(entity, "type").equals("explosive")) {
            Location loc = entity.getLocation();
            entity.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2, false, false, player);
        }
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        if (!Utils.isRightItem(player, getItem())) return;
        Utils.setStringInEntity(event.getEntity(), "type", "explosive");
    }
}
