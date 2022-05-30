package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Conjuring extends me.thomas.skyblock.items.SbItem implements Listener {

    public Conjuring() {
        super(new ItemStack(Material.STONE_SWORD), "Conjuring", 68, 36, 0, 0,
                85, 0, 0, Arrays.asList("Reduces the cooldown of Guided", "Sheep for &65 &7seconds, and",
                        "&7prevents Guided Sheep from", "spawning randomly while holding this item."), null, true, SbRarity.RARE_SWORD);
    }
}
