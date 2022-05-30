package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class LastBreath extends me.thomas.skyblock.items.SbItem implements Listener {

    public LastBreath() {
        super(new ItemStack(Material.BOW), "Last Breath", 200, 180, 0, 50,
                0, 0, 0, 0, Arrays.asList("&7Reduces the defense of your",
                        "&7target by &a10% &7of their max", "&a%defense%Defense &7on hit, stacking",
                        "&7up to &a5 &7 times."), null, true, SbRarity.LEGENDARY_BOW);
    }
}
