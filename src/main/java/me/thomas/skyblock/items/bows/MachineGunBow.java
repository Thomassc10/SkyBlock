package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class MachineGunBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public MachineGunBow() {
        super(new ItemStack(Material.BOW), "Machine Gun Bow", 75, 50, 0, 12, 0, 0, 0, null, Collections.singletonList(
                new SbAbility("Rapid Fire", AbilityType.LEFT_CLICK, Arrays.asList("&7Shoots &a5 &7Arrows per second",
                        "&7for &a8 &7seconds! Arrows deal", "&a70.0% &7of what they would", "&7normally deal."), 0, 100)), true, SbRarity.RARE_BOW);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        if (event.getType() != AbilityType.LEFT_CLICK) return;

        Player player = event.getPlayer();
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i == 40)
                    cancel();
                Arrow arrow = player.launchProjectile(Arrow.class);
                arrow.setVelocity(player.getLocation().getDirection().multiply(1));
                Utils.setIntInEntity(arrow, "machine", 1);
                i++;
            }

        }.runTaskTimer(SkyBlock.getInstance(), 1, 4);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) return;
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (event.getEntity().getLastDamageCause().getEntity().getType() == EntityType.ARROW) {
                if (Utils.getIntFromEntity(event.getEntity().getLastDamageCause().getEntity(), "warp") == 1)
                    event.setDamage(Utils.getPercent(event.getDamage(), 70));
            }
        }
    }
}
