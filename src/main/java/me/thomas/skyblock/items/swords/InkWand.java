package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class InkWand extends me.thomas.skyblock.items.SbItem implements Listener {

    public InkWand() {
        super(new ItemStack(Material.STICK), "Ink Wand", 130, 90, null, Collections.singletonList(
                new SbAbility("Ink Bomb", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoot an ink bomb in front of",
                        "&7you dealing &a10,000 &7damage", "&7and giving blindness!"), 60, 30)), true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {

    }
}
