package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;

public class RunaansBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public RunaansBow() {
        super(new ItemStack(Material.BOW), "Runaan's Bow", 160, 50, null, Collections.singletonList(
                new SbAbility("Triple Shot", AbilityType.NONE, Arrays.asList("&7Shoots 3 arrows at a time! The 2",
                        "&7extra arrows deal &a40%", "&7percent of the damage and home", "&7to targets."))), true, SbRarity.LEGENDARY_BOW);
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (!Utils.isRightItem(player, getItem())) return;
        Arrow arrow1 = player.launchProjectile(Arrow.class);
        Arrow arrow2 = player.launchProjectile(Arrow.class);

        Vector v = player.getLocation().getDirection().normalize();
        arrow1.setVelocity(new Vector(v.getX(), v.getY(), v.getZ() - 0.5).multiply(event.getForce()));
        arrow2.setVelocity(new Vector(v.getX() + 0.5, v.getY(), v.getZ()).multiply(event.getForce()));
        Utils.setIntInEntity(arrow1, "runaans", 1);
        Utils.setIntInEntity(arrow2, "runaans", 1);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
        if (!(event.getHitEntity() instanceof LivingEntity)) return;
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        SbItem sbItem = Items.getSbItem(player.getInventory().getItemInMainHand());
        if (Utils.getIntFromEntity(event.getEntity(), "runaans") == 1) {
            event.setCancelled(true);
            event.getEntity().remove();
            ((LivingEntity)event.getHitEntity()).damage(Utils.getPercent(Utils.getMeleeDamage(
                    PlayerManager.getPlayerManager().getSBPlayer(player), sbItem, false), 40));
        }
    }
}
