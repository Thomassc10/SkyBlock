package me.thomas.skyblock.items.specialitems;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class DctrsSpaceHelmet extends SbItem implements Listener {

    public DctrsSpaceHelmet() {
        super(new ItemStack(Material.RED_STAINED_GLASS), "Dctr's Space Helmet",
                Arrays.asList(ChatColor.ITALIC + "&7A rare space helmet forged", ChatColor.ITALIC + "&7from shards of moon glass", "",
                        "&7To: Unknown", "&7From: Unknown", "", "&8Edition #0", "&8January 1970"), null,true, SbRarity.SPECIAL_HELMET);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (event.getCurrentItem() == null) return;
        if (event.getSlotType() != InventoryType.SlotType.ARMOR) return;
        if (!event.getCursor().isSimilar(this.getItem())) return;
        Player player = (Player) event.getWhoClicked();
        player.getInventory().setHelmet(this.getItem());
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        player.getInventory().setHelmet(this.getItem());
        player.getInventory().getItemInMainHand().setType(Material.AIR);
    }
}
