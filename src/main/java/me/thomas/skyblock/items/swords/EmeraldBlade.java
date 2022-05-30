package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class EmeraldBlade extends me.thomas.skyblock.items.SbItem implements Listener {

    public EmeraldBlade() {
        super(new ItemStack(Material.EMERALD), "Emerald Blade", 130, 0, Arrays.asList("&7A powerful blade made from pure",
                "&2Emeralds&7. This blade becomes", "&7stronger as you carry more", "&6coins &7in your purse.", "",
                "&7Current Damage Bonus: &a0.0"), null, true, SbRarity.EPIC_SWORD);
    }
}
