package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class IceSprayWand extends me.thomas.skyblock.items.SbItem implements Listener {

    public IceSprayWand() {
        super(new ItemStack(Material.STICK), "Ice Spray Wand", 52, 0, 0, 0, 118, 0, 0, null, Collections.singletonList(
                new SbAbility("Ice Spray", AbilityType.RIGHT_CLICK, Arrays.asList("&7Produces a cone of ice in front",
                        "&7of the caster that deals", "&c17,000 &7damage to mobs and", "&7freezes them in place for &e5",
                        "&7seconds! Frozen mobs take", "&c10% &7increased damage!"), 50, 5)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

    }
}
