package me.thomas.skyblock.events;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(event.getPlayer());
        SbItem sbItem = Items.getSbItem(event.getItemDrop().getItemStack());
        if (sbItem == null) return;
        sbPlayer.removeStrenght(sbItem.getStrenght());
        sbPlayer.removeCriticalChance(sbItem.getCritChance());
        sbPlayer.removeCriticalDamage(sbItem.getCritDamage());
        sbPlayer.removeSpeed(sbItem.getSpeed());
        sbPlayer.removeDefense(sbItem.getDefense());
        sbPlayer.removeMaxMana(sbItem.getMana());
        sbPlayer.removeFerocity(sbItem.getFerocity());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Utils.scheduleTask(() -> {
            SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
            SbItem sbItem = Items.getSbItem(player.getInventory().getItemInMainHand());
            if (sbItem != null) {
                sbPlayer.addStrenght(sbItem.getStrenght());
                sbPlayer.addCriticalChance(sbItem.getCritChance());
                sbPlayer.addCriticalDamage(sbItem.getCritDamage());
                sbPlayer.addSpeed(sbItem.getSpeed());
                sbPlayer.addDefense(sbItem.getDefense());
                sbPlayer.addMaxMana(sbItem.getMana());
                sbPlayer.addFerocity(sbItem.getFerocity());
            }
        }, 5);
    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        SbItem sbItem = Items.getSbItem(event.getItem().getItemStack());
        if (sbItem != null) {
            sbPlayer.addStrenght(sbItem.getStrenght());
            sbPlayer.addCriticalChance(sbItem.getCritChance());
            sbPlayer.addCriticalDamage(sbItem.getCritDamage());
            sbPlayer.addSpeed(sbItem.getSpeed());
            sbPlayer.addDefense(sbItem.getDefense());
            sbPlayer.addMaxMana(sbItem.getMana());
            sbPlayer.addFerocity(sbItem.getFerocity());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() != player.getInventory()) return;
        if (player.getInventory().getHeldItemSlot() != event.getSlot()) return;
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        SbItem cursor = Items.getSbItem(event.getCursor());
        SbItem clicked = Items.getSbItem(event.getCurrentItem());
        if (event.isLeftClick() || event.isRightClick()) {
            if (clicked == null && cursor != null) {
                sbPlayer.addStrenght(cursor.getStrenght());
                sbPlayer.addCriticalChance(cursor.getCritChance());
                sbPlayer.addCriticalDamage(cursor.getCritDamage());
                sbPlayer.addSpeed(cursor.getSpeed());
                sbPlayer.addDefense(cursor.getDefense());
                sbPlayer.addMaxMana(cursor.getMana());
                sbPlayer.addFerocity(cursor.getFerocity());
            } else if (clicked != null && cursor != null) {
                sbPlayer.addStrenght(cursor.getStrenght() - clicked.getStrenght());
                sbPlayer.addCriticalChance(cursor.getCritChance() - clicked.getCritChance());
                sbPlayer.addCriticalDamage(cursor.getCritDamage() - clicked.getCritDamage());
                sbPlayer.addSpeed(cursor.getSpeed() - clicked.getSpeed());
                sbPlayer.addDefense(cursor.getDefense() - clicked.getDefense());
                sbPlayer.addMaxMana(cursor.getMana() - clicked.getMana());
                sbPlayer.addFerocity(cursor.getFerocity() - clicked.getFerocity());
            } else if (clicked != null) {
                sbPlayer.removeStrenght(clicked.getStrenght());
                sbPlayer.removeCriticalChance(clicked.getCritChance());
                sbPlayer.removeCriticalDamage(clicked.getCritDamage());
                sbPlayer.removeSpeed(clicked.getSpeed());
                sbPlayer.removeDefense(clicked.getDefense());
                sbPlayer.removeMaxMana(clicked.getMana());
                sbPlayer.removeFerocity(clicked.getFerocity());
            }
        }
        if (event.isShiftClick()) {
            if (clicked != null && event.getInventory().firstEmpty() != -1) {
                sbPlayer.removeStrenght(clicked.getStrenght());
                sbPlayer.removeCriticalChance(clicked.getCritChance());
                sbPlayer.removeCriticalDamage(clicked.getCritDamage());
                sbPlayer.removeSpeed(clicked.getSpeed());
                sbPlayer.removeDefense(clicked.getDefense());
                sbPlayer.removeMaxMana(clicked.getMana());
                sbPlayer.removeFerocity(clicked.getFerocity());
            }
        }
        if (event.getClick() == ClickType.NUMBER_KEY) {
            if (event.getSlot() == event.getHotbarButton()) return;
            SbItem slot = Items.getSbItem(player.getInventory().getItem(event.getHotbarButton()));
            if (slot != null && clicked != null) {
                sbPlayer.addStrenght(slot.getStrenght() - clicked.getStrenght());
                sbPlayer.addCriticalChance(slot.getCritChance() - clicked.getCritChance());
                sbPlayer.addCriticalDamage(slot.getCritDamage() - clicked.getCritDamage());
                sbPlayer.addSpeed(slot.getSpeed() - clicked.getSpeed());
                sbPlayer.addDefense(slot.getDefense() - clicked.getDefense());
                sbPlayer.addMaxMana(slot.getMana() - clicked.getMana());
                sbPlayer.addFerocity(slot.getFerocity() - clicked.getFerocity());
            } else if (slot == null && clicked != null) {
                sbPlayer.addStrenght(clicked.getStrenght());
                sbPlayer.addCriticalChance(clicked.getCritChance());
                sbPlayer.addCriticalDamage(clicked.getCritDamage());
                sbPlayer.addSpeed(clicked.getSpeed());
                sbPlayer.addDefense(clicked.getDefense());
                sbPlayer.addMaxMana(clicked.getMana());
                sbPlayer.addFerocity(clicked.getFerocity());
            } else if (slot != null) {
                sbPlayer.removeStrenght(slot.getStrenght());
                sbPlayer.removeCriticalChance(slot.getCritChance());
                sbPlayer.removeCriticalDamage(slot.getCritDamage());
                sbPlayer.removeSpeed(slot.getSpeed());
                sbPlayer.removeDefense(slot.getDefense());
                sbPlayer.removeMaxMana(slot.getMana());
                sbPlayer.removeFerocity(slot.getFerocity());
            }
        }
    }
}
