package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
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
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class FloridZombieSword extends me.thomas.skyblock.items.SbItem implements Listener {

    int taskID;

    public FloridZombieSword() {
        super(new ItemStack(Material.GOLDEN_SWORD), "Florid Zombie Sword", 150, 80, 0, 0, 100, 0, 0,
                Arrays.asList("&7Gain &a+2 &7extra max charges", "&7when you play as a &aHealer &7 in", "&7Dungeons."), Collections.singletonList(
                new SbAbility("Instant Heal", AbilityType.RIGHT_CLICK, Arrays.asList("&7Heal for &c168 &7+ &c5%❤ &7and",
                        "&7heal player within &a8 &7blocks", "&7for &c56❤&7."), 70, 15)), true, SbRarity.LEGENDARY_SWORD);
        Utils.setIntInItem(getItem(), "charges", 5);
    }

    @EventHandler
    public void onInteract(AbilityUseEvent event) {
        Player player = event.getPlayer();
        SbPlayer sbPlayer = event.getSbPlayer();

        int charges = Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "charges");
        if (charges > 0) {
            sbPlayer.regenHealth(168 + Utils.getPercent(sbPlayer.getMaxHealth(), 5));
            for (Player p : Utils.getNearestPlayers(player, 8)) {
                if (p != null) {
                    SbPlayer pl = PlayerManager.getPlayerManager().getSBPlayer(p);
                    pl.regenHealth(56);
                }
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
                if (charges < 5)
                    Utils.setIntInItem(event.getItem(), "charges", charges + 1);
            }, 1, 300);
        }
        else if (event.getDestination().getType() != InventoryType.PLAYER)
            Bukkit.getScheduler().cancelTask(taskID);
    }
}
