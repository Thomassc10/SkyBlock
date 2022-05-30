package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class AspectOfTheJerry extends SbItem implements Listener {

    public AspectOfTheJerry() {
        super(new ItemStack(Material.WOODEN_SWORD), "Aspect Of The Jerry", 1, 0, null,
                Collections.singletonList(new SbAbility("Parley", AbilityType.RIGHT_CLICK, Collections.singletonList("&7Channel you inner Jerry."),
                        1, 5)), true, SbRarity.COMMON_SWORD);
    }

    @EventHandler
    public void onInteract(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
    }
}
