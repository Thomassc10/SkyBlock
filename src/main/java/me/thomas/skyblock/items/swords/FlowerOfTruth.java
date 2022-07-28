package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import net.minecraft.core.Vector3f;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlowerOfTruth extends me.thomas.skyblock.items.SbItem implements Listener {

    public FlowerOfTruth() {
        super(new ItemStack(Material.POPPY), "Flower Of Truth", 160, 300, null, Collections.singletonList(
                new SbAbility("Heat-Seeking Rose", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots a rose that ricochets",
                        "&7between enemies, damaging up to", "&a3 &7of your foes! Damage",
                        "&7multiplies as more enemies are", "&7hit.", "&7The mana cost of this item is",
                        "&a10.0% &7of your maximum mana."), 10, 1)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        Location loc = player.getLocation();

        ArmorStand armorStand = player.getWorld().spawn(loc.clone().add(0, 1, 0), ArmorStand.class, stand -> {
            stand.setInvisible(true);
            stand.setInvulnerable(true);
            stand.setGravity(false);
            stand.getEquipment().setItemInMainHand(new ItemStack(Material.POPPY));
            stand.setRightArmPose(new EulerAngle(Math.toRadians(15), 0, 0));
        });

        new BukkitRunnable() {
            boolean first = false;
            final double damage = Utils.getMeleeDamage(event.getSbPlayer(), event.getSbItem(), false);
            @Override
            public void run() {

                List<LivingEntity> near = null;
                if (!first) {
                    List<LivingEntity> entities = Utils.getNearestEntities(armorStand,0.5);
                    if (entities.isEmpty()) {
                        armorStand.teleport(armorStand.getLocation().add(loc.getDirection().normalize()));
                        return;
                    }
                    LivingEntity entity = entities.get(0);

                    entity.damage(damage);
                    first = true;
                    near = Utils.getNearestEntities(armorStand, 3);
                    if (near.isEmpty()) {
                        cancel();
                        armorStand.remove();
                    }
                }
                if (first) {
                    armorStand.setVelocity(near.get(0).getLocation().getDirection().multiply(3));
                    near.get(0).damage(damage);
                    if (near.size() == 2) {
                        armorStand.setVelocity(near.get(1).getLocation().getDirection().multiply(3));
                        near.get(1).damage(damage);
                    }
                    cancel();
                    armorStand.remove();
                }

                if (armorStand.getLocation().distance(loc) >= 50) {
                    armorStand.remove();
                    cancel();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);
    }
}
