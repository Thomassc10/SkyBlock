package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class SpiritSceptre extends me.thomas.skyblock.items.SbItem implements Listener {

    public SpiritSceptre() {
        super(new ItemStack(Material.ALLIUM), "Spirit Sceptre", 180, 0, 0, 0, 300, 0, 0, null, Collections.singletonList(
                new SbAbility("Guider Bat", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots a guided spirit bat,",
                        "&7following your aim and exploding", "&7for &c2,000 &7damage."), 250)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        Bat bat = player.getWorld().spawn(player.getEyeLocation(), Bat.class);
        bat.setInvulnerable(true);

        new BukkitRunnable() {

            @Override
            public void run() {
                bat.setVelocity(player.getLocation().getDirection().multiply(1));

                LivingEntity lv = Utils.getNearestEntity(bat, 0.5);
                if (bat.getLocation().getBlock().getType() != Material.AIR || lv != null) {
                    player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, bat.getLocation(), 1);
                    for (LivingEntity e : Utils.getNearestEntities(bat, 3)) {
                        if (e != null)
                            e.damage(2000);
                    }
                    cancel();
                    bat.remove();
                }
                if (bat.getLocation().distance(player.getLocation()) >= 50) {
                    cancel();
                    bat.remove();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);
    }
}
