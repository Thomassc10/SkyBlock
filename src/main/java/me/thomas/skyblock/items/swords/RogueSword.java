package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class RogueSword extends SbItem implements Listener {

    public RogueSword() {
        super(new ItemStack(Material.GOLDEN_SWORD), "Rogue Sword", 20, 0, null,
                Collections.singletonList(new SbAbility("Speed Boost", AbilityType.RIGHT_CLICK, Collections.singletonList("&7Grants &f+100✦Speed &for &a30s&7."),
                        50)), true, SbRarity.COMMON_SWORD);
    }

    /*@EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!Utils.isRightItem(event.getPlayer(), getItem())) return;
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(event.getPlayer());
        if (!Utils.isRightClick(event.getAction())) return;

        sbPlayer.addSpeed(100);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.getInstance(), () -> sbPlayer.removeSpeed(100), 600);
    }*/

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        event.getSbPlayer().addSpeed(100);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.getInstance(), () -> event.getSbPlayer().removeSpeed(100), 600);
    }
}
