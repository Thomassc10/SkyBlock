package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SoulstealerBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public SoulstealerBow() {
        super(new ItemStack(Material.BOW), "Soulstealer Bow", 50, 0, 0, 0, 157,
                0, 0, 0, Arrays.asList("&7Replaces the arrow that you",
                        "&7shoot with exploding wither", "&7skulls!"), null, true, SbRarity.RARE_BOW);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        event.setCancelled(true);
        WitherSkull skull = player.launchProjectile(WitherSkull.class);
        skull.setVelocity(player.getLocation().getDirection().multiply(1));
    }
}
