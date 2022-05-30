package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import net.minecraft.core.Vector3f;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;

public class Bonemerang extends me.thomas.skyblock.items.SbItem implements Listener {

    public Bonemerang() {
        super(new ItemStack(Material.BONE), "Bonemerang", 270, 130, null, Collections.singletonList(
                new SbAbility("Swing", AbilityType.RIGHT_CLICK, Arrays.asList("&7Throw the bone a short distance,",
                        "&7dealing the damage an arrow", "&7would.", "", "&7Deals &cdouble damage &7when",
                        "&7coming back. Pierces up to &e10", "&7foes."))), true, SbRarity.LEGENDARY_BOW);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        if (event.getSbItem().getItem().getType() != Material.BONE) return;

        EntityArmorStand stand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
        stand.setInvisible(true);
        stand.setInvulnerable(true);
        stand.setArms(true);
        stand.setRightArmPose(new Vector3f(0, (float) Math.toRadians(90), 0));
        stand.setMarker(true);
        stand.setNoGravity(true);
        EntityEquipment equipment = ((LivingEntity) stand.getBukkitEntity()).getEquipment();
        equipment.setItemInMainHand(new ItemStack(Material.BONE));
        Location loc = player.getLocation();
        stand.setPosition(loc.getX(), loc.getY() + 0.25, loc.getZ());

        WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
        world.addEntity(stand);
        event.getSbItem().getItem().setType(Material.GHAST_TEAR);

        new BukkitRunnable() {
            int i = 0;
            final Vector v = loc.clone().getDirection().normalize();
            final double damage = Utils.getMeleeDamage(event.getSbPlayer(), event.getSbItem());
            @Override
            public void run() {
                stand.setHeadRotation(i);
                i++;
                if (!Utils.getStringFromEntity(stand.getBukkitEntity(), "bonemerang").equals("back")) {
                    stand.getBukkitEntity().setVelocity(v.multiply(1));
                    for (LivingEntity e : Utils.getNearestEntities(stand.getBukkitEntity(), 0.5))
                        if (e != null)
                            e.damage(damage);
                } else {
                    stand.getBukkitEntity().setVelocity(player.getLocation().clone().subtract(stand.getBukkitEntity().getLocation()).toVector().normalize().multiply(1));
                    for (LivingEntity e : Utils.getNearestEntities(stand.getBukkitEntity(), 0.5))
                        if (e != null)
                            e.damage(damage * 2);
                }
                if (stand.getBukkitEntity().getLocation().distance(loc) >= 6) {
                    Utils.setStringInEntity(stand.getBukkitEntity(), "bonemerang", "back");
                }
                if (stand.getBukkitEntity().getLocation().distance(loc) <= 1 && Utils.getStringFromEntity(stand.getBukkitEntity(), "bonemerang").equals("back")) {
                    event.getSbItem().getItem().setType(Material.BONE);
                    cancel();
                    stand.getBukkitEntity().remove();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);
    }
}
