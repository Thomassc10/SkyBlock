package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AspectOfTheDragons extends me.thomas.skyblock.items.SbItem implements Listener {

    public AspectOfTheDragons() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Aspect Of The Dragons", 225, 100, null, Collections.singletonList(
                new SbAbility("Dragon Rage", AbilityType.RIGHT_CLICK, Arrays.asList("&7All Monsters in front of you",
                        "&7take &a12,000 &7damage. Hit", "&7monsters take large knockback."), 100)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        List<LivingEntity> living = Utils.getNearestEntities(player, 4);
        for (LivingEntity lv : living) {
            if (!(lv instanceof Player))
                if (player.hasLineOfSight(lv)) {
                    lv.damage(12000);
                    lv.setVelocity(lv.getLocation().clone().subtract(player.getLocation()).toVector().normalize());
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                }
        }
    }
}
