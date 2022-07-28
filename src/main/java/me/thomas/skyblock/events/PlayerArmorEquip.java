package me.thomas.skyblock.events;

import me.thomas.skyblock.events.customevents.armorequip.ArmorEquipEvent;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbArmor;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerArmorEquip implements Listener {

    @EventHandler
    public void onEquip(ArmorEquipEvent event) {
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(event.getPlayer());
        SbArmor oldItem = Items.getSbArmor(event.getOldArmorPiece());
        SbArmor newItem = Items.getSbArmor(event.getNewArmorPiece());

        if (newItem != null && oldItem != null) {
            sbPlayer.addMaxHealth(newItem.getHealth() - oldItem.getHealth());
            sbPlayer.addDefense(newItem.getDefense() - oldItem.getDefense());
            sbPlayer.addStrenght(newItem.getStrenght() - oldItem.getStrenght());
            sbPlayer.addCriticalChance(newItem.getCritChance() - oldItem.getCritChance());
            sbPlayer.addCriticalDamage(newItem.getCritDamage() - oldItem.getCritDamage());
            sbPlayer.addSpeed(newItem.getSpeed() - oldItem.getSpeed());
            sbPlayer.addDefense(newItem.getDefense() - oldItem.getDefense());
            sbPlayer.addMaxMana(newItem.getMana() - oldItem.getMana());
            sbPlayer.addFerocity(newItem.getFerocity() - oldItem.getFerocity());
        } else if (oldItem == null && newItem != null) {
            sbPlayer.addMaxHealth(newItem.getHealth());
            sbPlayer.addDefense(newItem.getDefense());
            sbPlayer.addStrenght(newItem.getStrenght());
            sbPlayer.addCriticalChance(newItem.getCritChance());
            sbPlayer.addCriticalDamage(newItem.getCritDamage());
            sbPlayer.addSpeed(newItem.getSpeed());
            sbPlayer.addDefense(newItem.getDefense());
            sbPlayer.addMaxMana(newItem.getMana());
            sbPlayer.addFerocity(newItem.getFerocity());
        } else if (oldItem != null) {
            sbPlayer.removeMaxHealth(oldItem.getHealth());
            sbPlayer.removeDefense(oldItem.getDefense());
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
