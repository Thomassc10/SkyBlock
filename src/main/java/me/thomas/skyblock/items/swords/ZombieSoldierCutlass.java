package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class ZombieSoldierCutlass extends me.thomas.skyblock.items.SbItem implements Listener {

    public ZombieSoldierCutlass() {
        super(new ItemStack(Material.IRON_SWORD), "Zombie Soldier Cutlass", 52, 26, null, Collections.singletonList(
                new SbAbility("Love Tap", AbilityType.NONE, Arrays.asList("&7Heals you for &c+10❤Health",
                        "&7when you hit an entity!"))), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        Player player = (Player) event.getDamager();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        sbPlayer.regenHealth(10);
    }
}
