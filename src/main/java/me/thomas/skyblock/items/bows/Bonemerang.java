package me.thomas.skyblock.items.bows;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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
        ItemStack item = player.getInventory().getItemInMainHand();
        Location loc = player.getLocation();
        ArmorStand armorStand = player.getWorld().spawn(loc, ArmorStand.class, stand -> {
            stand.setInvulnerable(true);
            stand.setInvulnerable(true);
            stand.setGravity(false);
            stand.getEquipment().setHelmet(new ItemStack(Material.BONE));
        });
        item.setType(Material.GHAST_TEAR);

        new BukkitRunnable() {
            int i = 0;
            final double damage = Utils.getMeleeDamage(event.getSbPlayer(), event.getSbItem(), false);
            @Override
            public void run() {
                armorStand.setRotation(i, i);
                i+=5;
                if (!Utils.getStringFromEntity(armorStand, "bonemerang").equals("back")) {
                    armorStand.teleport(armorStand.getLocation().add(loc.getDirection().normalize().multiply(0.3)));
                    for (LivingEntity e : Utils.getNearestEntities(armorStand, 0.2))
                        if (e != null)
                            e.damage(damage);
                } else {
                    armorStand.teleport(armorStand.getLocation().add(player.getLocation().clone().subtract(armorStand.getLocation()).toVector().normalize().multiply(0.4)));
                    for (LivingEntity e : Utils.getNearestEntities(armorStand, 0.2))
                        if (e != null)
                            e.damage(damage * 2);
                }

                if (armorStand.getLocation().distance(loc) >= 8)
                    Utils.setStringInEntity(armorStand, "bonemerang", "back");

                if (armorStand.getLocation().distance(loc) <= 1 && Utils.getStringFromEntity(armorStand, "bonemerang").equals("back")) {
                    item.setType(Material.BONE);
                    cancel();
                    armorStand.remove();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);
    }
}
