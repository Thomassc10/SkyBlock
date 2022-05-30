package me.thomas.skyblock.events;

import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HeldItem implements Listener {

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);

        SbItem oldItem = Items.getSbItem(player.getInventory().getItem(event.getPreviousSlot()));
        SbItem newItem = Items.getSbItem(player.getInventory().getItem(event.getNewSlot()));

        if (newItem != null && oldItem != null) {
            sbPlayer.addStrenght(newItem.getStrenght() - oldItem.getStrenght());
            sbPlayer.addCriticalChance(newItem.getCritChance() - oldItem.getCritChance());
            sbPlayer.addCriticalDamage(newItem.getCritDamage() - oldItem.getCritDamage());
            sbPlayer.addSpeed(newItem.getSpeed() - oldItem.getSpeed());
            sbPlayer.addDefense(newItem.getDefense() - oldItem.getDefense());
            sbPlayer.addMaxMana(newItem.getMana() - oldItem.getMana());
            sbPlayer.addFerocity(newItem.getFerocity() - oldItem.getFerocity());
        } else if (newItem != null) {
            sbPlayer.addStrenght(newItem.getStrenght());
            sbPlayer.addCriticalChance(newItem.getCritChance());
            sbPlayer.addCriticalDamage(newItem.getCritDamage());
            sbPlayer.addSpeed(newItem.getSpeed());
            sbPlayer.addDefense(newItem.getDefense());
            sbPlayer.addMaxMana(newItem.getMana());
            sbPlayer.addFerocity(newItem.getFerocity());
        } else if (oldItem != null) {
            sbPlayer.removeStrenght(oldItem.getStrenght());
            sbPlayer.removeCriticalChance(oldItem.getCritChance());
            sbPlayer.removeCriticalDamage(oldItem.getCritDamage());
            sbPlayer.removeSpeed(oldItem.getSpeed());
            sbPlayer.removeDefense(oldItem.getDefense());
            sbPlayer.removeMaxMana(oldItem.getMana());
            sbPlayer.removeFerocity(oldItem.getFerocity());
        }
    }
}
