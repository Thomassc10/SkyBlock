package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SniperBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public SniperBow() {
        super(new ItemStack(Material.BOW), "Sniper Bow", 94, 0, 0, 1, 0,
                0, 0, Arrays.asList("&7Allows you to shoot arrows much", "&7further"), null, true, SbRarity.RARE_BOW);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        event.setCancelled(true);

        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVelocity(player.getLocation().getDirection().multiply(2));
    }
}
