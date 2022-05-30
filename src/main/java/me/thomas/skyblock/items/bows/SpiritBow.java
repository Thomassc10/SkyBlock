package me.thomas.skyblock.items.bows;

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

public class SpiritBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public SpiritBow() {
        super(new ItemStack(Material.BOW), "Spirit Bow", 210, 100, Arrays.asList(
                "&7Deals &a+2% &7damage to &aUndead", "&amonsters &7for every &a1% &7of",
                "&7your missing health.", "", "&bSpirit Item: &7When turned", "&7into a ghost, this item becomes",
                "&7a Ghost Ability."), Collections.singletonList(new SbAbility("Spirit Bomb", AbilityType.NONE,
                Arrays.asList("&7Shoots a spirit that deals", "&c8,000 &7damage on impact."))), true, SbRarity.LEGENDARY_BOW);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (!Utils.isUndead(event.getEntity())) return;
        Player player = (Player) event.getDamager();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        int percent = (int) Utils.getPercent(sbPlayer.getMaxHealth(), 1);
        double amount = ((sbPlayer.getMaxHealth() - player.getHealth()) / percent) * 2;
        event.setDamage(event.getDamage() + Utils.getPercent(event.getDamage(), (int) amount));
    }
}
