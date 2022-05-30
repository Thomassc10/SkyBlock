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
        SbArmor oldArmor = Items.getSbArmor(event.getOldArmorPiece());
        SbArmor newArmor = Items.getSbArmor(event.getNewArmorPiece());

        if (newArmor != null && oldArmor != null) {
            sbPlayer.addMaxHealth(newArmor.getHealth() - oldArmor.getHealth());
            sbPlayer.addDefense(newArmor.getDefense() - oldArmor.getDefense());
        } else if (newArmor == null && oldArmor != null) {
            sbPlayer.removeMaxHealth(oldArmor.getHealth());
            sbPlayer.removeDefense(oldArmor.getDefense());
        } else if (newArmor != null) {
            sbPlayer.addMaxHealth(newArmor.getHealth());
            sbPlayer.addDefense(newArmor.getDefense());
        }
    }
}
