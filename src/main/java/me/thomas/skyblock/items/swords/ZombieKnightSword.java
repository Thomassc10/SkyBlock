package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class ZombieKnightSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public ZombieKnightSword() {
        super(new ItemStack(Material.IRON_SWORD), "Zombie Knight Sword", 82, 21,
                null, Collections.singletonList(new SbAbility("Zombie Knight", AbilityType.FULL_SET_BONUS,
                        Arrays.asList("&7Gains &c+30 &7Strenght when", "&7used with the Zombie Knight",
                                "&7Armor."))), true, SbRarity.EPIC_SWORD);
    }
}
