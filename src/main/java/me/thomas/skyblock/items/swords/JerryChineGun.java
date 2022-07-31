package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
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

public class JerryChineGun extends me.thomas.skyblock.items.SbItem implements Listener {

    public JerryChineGun() {
        super(new ItemStack(Material.LEATHER_HORSE_ARMOR), "Jerry-chine Gun", 80, 0, 0, 0, 200, 0, 0,
                Arrays.asList("&7Each shot has &a+30 &7mana cost", "&7than the previous one, resetting",
                        "&7after &a4s &7without shooting."), Collections.singletonList(new SbAbility("Rapid-fire", AbilityType.RIGHT_CLICK,
                        Arrays.asList("&7Shoots a Jerry bullet, dealing", "&c500 &7damage on impact and",
                                "&7knocking you back."), 30)), true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        ItemStack jerry = Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNhOGVmMjQ1OGEyYjEwMjYwYjg3NTY1NThmNzY3OWJjYjdlZjY5MWQ0MWY1MzRlZmVhMmJhNzUxMDczMTVjYyJ9fX0=");
        Player player = event.getPlayer();
        //EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class, a -> {
            a.setInvisible(true);
            a.setInvulnerable(true);
            EntityEquipment equipment = a.getEquipment();
            equipment.setHelmet(jerry);
        });


        new BukkitRunnable() {
            final Location loc = player.getLocation();
            @Override
            public void run() {
                //armorStand.setVelocity(loc.getDirection().multiply(2));
                armorStand.teleport(armorStand.getLocation().add(loc.getDirection().normalize().multiply(0.5)));

                List<LivingEntity> living = Utils.getNearestEntities(armorStand, 0.1);
                if (living.isEmpty()) return;
                for (LivingEntity entity : living) {
                    if (!(entity instanceof Player)) {
                        armorStand.remove();
                        entity.damage(500);
                        cancel();
                    }
                }

                if (armorStand.getLocation().distance(loc) >= 50) {
                    armorStand.remove();
                    cancel();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);
    }
}
