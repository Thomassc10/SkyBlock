package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PigmanSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public PigmanSword() {
        super(new ItemStack(Material.GOLDEN_SWORD), "Pigman Sword", 200, 100, 5, 30, 300, 0, 0, null, Collections.singletonList(
                new SbAbility("Burning Souls", AbilityType.RIGHT_CLICK, Arrays.asList("&7Gain &a+300%defense% Defense &7for",
                        "&7&a5s &7and cast vortex of flames", "&7towards enemies, dealing up to",
                        "&c30,000 &7over &a5 &7seconds."), 400, 5)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        SbPlayer sbPlayer = event.getSbPlayer();
        sbPlayer.addDefense(300);
        Utils.scheduleTask(() -> sbPlayer.removeDefense(300), 1);

        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                List<LivingEntity> living = Utils.getNearestEntities(player, 3);
                for (LivingEntity lv : living) {
                    if (!(lv instanceof Player))
                        if (player.hasLineOfSight(lv)) {
                            lv.damage(6000);
                        }
                }
                if (i == 5)
                    cancel();
                i++;
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 20);
    }
}
