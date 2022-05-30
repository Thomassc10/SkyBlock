package me.thomas.skyblock.events.customevents.fullset;

import me.thomas.skyblock.events.customevents.armorequip.ArmorEquipEvent;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbArmor;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EquipSet implements Listener {

    @EventHandler
    public void onEquip(ArmorEquipEvent event) {
        Player player = event.getPlayer();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        switch (event.getType()) {
            case HELMET: {
                helmet = event.getNewArmorPiece();
                SbArmor[] set = {Items.getSbArmor(helmet), Items.getSbArmor(chestplate), Items.getSbArmor(leggings), Items.getSbArmor(boots)};
                if (chestplate != null && leggings != null && boots != null) {
                    if (Items.getSets().containsValue(set)) {
                        Bukkit.getPluginManager().callEvent(new FullSetEquipEvent(player, set, sbPlayer));
                    } else {
                        set[0] = Items.getSbArmor(event.getOldArmorPiece());
                        Bukkit.getPluginManager().callEvent(new FullSetUnequipEvent(player, set, sbPlayer));
                    }
                }
            }
            break;
            case CHESTPLATE: {
                chestplate = event.getNewArmorPiece();
                SbArmor[] set = {Items.getSbArmor(helmet), Items.getSbArmor(chestplate), Items.getSbArmor(leggings), Items.getSbArmor(boots)};
                if (helmet != null && leggings != null && boots != null) {
                    if (Items.getSets().containsValue(set)) {
                        Bukkit.getPluginManager().callEvent(new FullSetEquipEvent(player, set, sbPlayer));
                    } else {
                        set[1] = Items.getSbArmor(event.getOldArmorPiece());
                        Bukkit.getPluginManager().callEvent(new FullSetUnequipEvent(player, set, sbPlayer));
                    }
                }
            }
            break;
            case LEGGINGS: {
                leggings = event.getNewArmorPiece();
                SbArmor[] set = {Items.getSbArmor(helmet), Items.getSbArmor(chestplate), Items.getSbArmor(leggings), Items.getSbArmor(boots)};
                if (chestplate != null && helmet != null && boots != null) {
                    if (Items.getSets().containsValue(set)) {
                        Bukkit.getPluginManager().callEvent(new FullSetEquipEvent(player, set, sbPlayer));
                    } else {
                        set[2] = Items.getSbArmor(event.getOldArmorPiece());
                        Bukkit.getPluginManager().callEvent(new FullSetUnequipEvent(player, set, sbPlayer));
                    }
                }
            }
            break;
            case BOOTS: {
                boots = event.getNewArmorPiece();
                SbArmor[] set = {Items.getSbArmor(helmet), Items.getSbArmor(chestplate), Items.getSbArmor(leggings), Items.getSbArmor(boots)};
                if (chestplate != null && leggings != null && helmet != null) {
                    if (Items.getSets().containsValue(set)) {
                        Bukkit.getPluginManager().callEvent(new FullSetEquipEvent(player, set, sbPlayer));
                    } else {
                        set[3] = Items.getSbArmor(event.getOldArmorPiece());
                        Bukkit.getPluginManager().callEvent(new FullSetUnequipEvent(player, set, sbPlayer));
                    }
                }
            }
            break;
        }
    }
}
