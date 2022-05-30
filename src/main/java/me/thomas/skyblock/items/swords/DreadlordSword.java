package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class DreadlordSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public DreadlordSword() {
        super(new ItemStack(Material.IRON_SWORD), "Dreadlord Sword", 52, 50, 0, 0, 85, 0, 0, null, Collections.singletonList(
                new SbAbility("Dreadlord", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots a skull that deals &a500",
                        "&7damage."), 40)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (Utils.isRightClick(event.getAction())) {
            WitherSkull skull = player.launchProjectile(WitherSkull.class);
            skull.setCharged(true);
            skull.setIsIncendiary(false);
        }
    }
}
