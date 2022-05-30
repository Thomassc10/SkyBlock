package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class EmberRod extends me.thomas.skyblock.items.SbItem implements Listener {

    public EmberRod() {
        super(new ItemStack(Material.BLAZE_ROD), "Ember Rod", 80, 35, 0, 0, 50, 0, 0, null, Collections.singletonList(
                new SbAbility("Fire Blast", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots 3 Fireballs which deal",
                        "&c30 &7damage in rapid", "&7succession in front of you!"), 150, 30)), true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (Utils.isRightClick(event.getAction())) {
            new BukkitRunnable() {
                int times = 0;
                @Override
                public void run() {
                    if (times < 3)
                        player.launchProjectile(Fireball.class);
                    else cancel();
                    times++;
                }
            }.runTaskTimer(SkyBlock.getInstance(), 1, 5);
        }
    }
}
