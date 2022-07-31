package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Conjuring extends me.thomas.skyblock.items.SbItem implements Listener {

    public Conjuring() {
        super(new ItemStack(Material.STONE_SWORD), "Conjuring", 68, 36, 0, 0,
                85, 0, 0, Arrays.asList("&7Reduces the cooldown of Guided", "&7Sheep for &65 &7seconds, and",
                        "&7prevents Guided Sheep from", "&7spawning randomly while holding this item."), null, true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        Sheep sheep = player.getWorld().spawn(player.getEyeLocation(), Sheep.class, s -> {
            s.setInvulnerable(true);
            s.setAI(false);
        });
    }
}
