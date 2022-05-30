package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BonzoStaff extends me.thomas.skyblock.items.SbItem implements Listener {

    private final ItemStack[] balloons = new ItemStack[]{
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJkZDExZGEwNDI1MmY3NmI2OTM0YmMyNjYxMmY1NGYyNjRmMzBlZWQ3NGRmODk5NDEyMDllMTkxYmViYzBhMiJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYzODdmYzI0Njg5M2Q5MmE2ZGQ5ZWExYjUyZGNkNTgxZTk5MWVlZWUyZTI2M2IyN2ZmZjFiY2YxYjE1NGViNyJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY4NTUyMmVlODE1ZDExMDU4N2ZmZmM3NDExM2Y0MTlkOTI5NTk4ZTI0NjNiOGNlOWQzOWNhYTlmYjZmZjVhYiJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVmMTYyZGVmODQ1YWEzZGM3ZDQ2Y2QwOGE3YmY5NWJiZGZkMzJkMzgxMjE1YWE0MWJmZmFkNTIyNDI5ODcyOCJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTI2ZWM3Y2QzYjZhZTI0OTk5NzEzN2MxYjk0ODY3YzY2ZTk3NDk5ZGEwNzFiZjUwYWRmZDM3MDM0MTMyZmEwMyJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2EyZGYzMTViNDM1ODNiMTg5NjIzMWI3N2JhZTFhNTA3ZGJkN2U0M2FkODZjMWNmYmUzYjJiOGVmMzQzMGU5ZSJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg2OGU2YTVjNGE0NDVkNjBhMzA1MGI1YmVjMWQzN2FmMWIyNTk0Mzc0NWQyZDQ3OTgwMGM4NDM2NDg4MDY1YSJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA1MmJlMWMwNmE0YTMyNTEyOWQ2ZjQxYmI4NGYwZWExY2E2ZjlmNjllYmRmZmY0MzE2ZTc0MjQ1MWM3OWMyMSJ9fX0="),
            Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IxYWU3YTQ3MTcyOTY1MWI1NjY3YjgxNjk0ZTQ5MjgwOGM1MDkwYzJiMTY4ZjBhOTE5MGZkMDAyZWU1MGEyNiJ9fX0=")
    };

    public BonzoStaff() {
        super(new ItemStack(Material.BLAZE_ROD), "Bonzo Staff", 160, 0, 0, 0, 250, 0, 0, null, Collections.singletonList(
                new SbAbility("Showtime", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots balloons that create a",
                        "&7large explosion on impact,", "&7dealing up to *c1,000 &7damage."), 100)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        EntityEquipment equipment = ((LivingEntity) armorStand.getBukkitEntity()).getEquipment();
        equipment.setHelmet(balloons[new Random().nextInt(balloons.length)]);

        Location loc = player.getLocation();
        armorStand.setPosition(loc.getX(), loc.getY(), loc.getZ());
        WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
        world.addEntity(armorStand);


        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                armorStand.getBukkitEntity().setVelocity(loc.getDirection().multiply(1));
                armorStand.getBukkitEntity().setRotation(i, i);
                i+=5;

                List<LivingEntity> living = Utils.getNearestEntities(armorStand.getBukkitEntity(), 0.5);
                if (living.isEmpty()) return;
                for (LivingEntity entity : living) {
                    if (!(entity instanceof Player)) {
                        armorStand.getBukkitEntity().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, armorStand.getBukkitEntity().getLocation(), 1);
                        armorStand.getBukkitEntity().remove();
                        entity.damage(1000);
                        cancel();
                    }
                }

                if (armorStand.getBukkitEntity().getLocation().distance(loc) >= 50) {
                    armorStand.getBukkitEntity().remove();
                    cancel();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);

    }
}
