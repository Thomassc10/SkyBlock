package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class TacticiansSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public TacticiansSword() {
        super(new ItemStack(Material.WOODEN_SWORD), "Tactician's Sword", 50, 0, 20, 0,
                0, 0, 0, Arrays.asList("&7Gains &c+15 Damage &7 for", "&7each Combat collections of",
                        "&7Tier VII and over of its", "&7wearer.", "", "&7Your Collections: &e0 &7/ &e10"), null, true, SbRarity.RARE_SWORD);
    }
}
