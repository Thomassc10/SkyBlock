package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
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

public class ShadowFury extends me.thomas.skyblock.items.SbItem implements Listener {

    public ShadowFury() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Shadow Fury", 300, 125, 0, 0, 0, 30, 0, null, Collections.singletonList(
                new SbAbility("Shadow Fury", AbilityType.RIGHT_CLICK, Arrays.asList("&7Rapidly teleports you to up to",
                        "&b5 &7enemies within &e12", "&7blocks, rooting each of them",
                        "&7and allowing you to hit them."), 0, 15)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        List<LivingEntity> lvs = Utils.getNearestEntities(player, 12);
        if (lvs.isEmpty()) return;

        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (lvs.size() == 1) {
                    player.teleport(lvs.get(0).getLocation().add(lvs.get(0).getLocation().getDirection().multiply(-1)));
                    cancel();
                }
                if (i < (lvs.size() - 1)) {
                    player.teleport(lvs.get(i).getLocation().add(lvs.get(i).getLocation().getDirection().multiply(-1)));
                } else cancel();
                i++;
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 10);
    }
}
