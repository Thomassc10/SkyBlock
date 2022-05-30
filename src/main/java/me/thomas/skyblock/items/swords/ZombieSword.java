package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class ZombieSword extends me.thomas.skyblock.items.SbItem implements Listener {

    int taskID;

    public ZombieSword() {
        super(new ItemStack(Material.IRON_SWORD), "Zombie Sword", 100, 50, 0, 0, 50, 0, 0, null, Collections.singletonList(
                new SbAbility("Instant Heal", AbilityType.RIGHT_CLICK, Arrays.asList("&7Heal for &c120 + 5%❤ &7and",
                        "&7heal player within &a7 &7blocks", "&7for &c40❤&7."), 70, 15)), true, SbRarity.RARE_SWORD);
        Utils.setIntInItem(getItem(), "charges", 4);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Utils.isRightItem(event.getPlayer(), getItem())) return;
        if (!Utils.isRightClick(event.getAction())) return;
        Player player = event.getPlayer();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);

        int charges = Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "charges");
        if (charges > 0) {
            sbPlayer.regenHealth(120 + Utils.getPercent(sbPlayer.getMaxHealth(), 5));
            for (Player p : Utils.getNearestPlayers(player, 7)) {
                SbPlayer pl = PlayerManager.getPlayerManager().getSBPlayer(p);
                pl.regenHealth(40);
            }
            Utils.setIntInItem(player.getInventory().getItemInMainHand(), "charges", charges - 1);
        }
    }

    @EventHandler
    public void onMove(InventoryMoveItemEvent event) {
        if (!event.getItem().isSimilar(getItem())) return;
        if (event.getDestination().getType() == InventoryType.PLAYER) {
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.getInstance(), () -> {
                int charges = Utils.getIntFromItem(event.getItem(), "charges");
                if (charges < 4)
                    Utils.setIntInItem(event.getItem(), "charges", charges + 1);
            }, 1, 300);
        }
        else if (event.getDestination().getType() != InventoryType.PLAYER)
            Bukkit.getScheduler().cancelTask(taskID);
    }
}