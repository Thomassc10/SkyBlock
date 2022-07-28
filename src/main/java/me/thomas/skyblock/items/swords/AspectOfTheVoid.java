package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class AspectOfTheVoid extends me.thomas.skyblock.items.SbItem implements Listener {

    public AspectOfTheVoid() {
        super(new ItemStack(Material.DIAMOND_SHOVEL), "Aspect of the Void", 120, 100, null,
                Collections.singletonList(new SbAbility("Instant Transmission", AbilityType.RIGHT_CLICK,
                        Arrays.asList("&7Teleport &a8 blocks &7ahead of",
                                "&7you and gain &a+50 &f✦ Speed", "&7for &a3 seconds&7."), 45)), true, SbRarity.EPIC_SWORD);
        Utils.setIntInItem(getItem(), "speed", 0);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        SbPlayer sbPlayer = event.getSbPlayer();
        Block block = player.getTargetBlock(null, 8);
        player.teleport(block.getLocation().clone().add(0,1,0).setDirection(player.getLocation().getDirection()));
        ItemStack item = player.getInventory().getItemInMainHand();
        int time = Utils.getIntFromItem(item, "speed");
        if (time > 0) {
            Utils.setIntInItem(item, "speed", (time + 3));
        } else {
            sbPlayer.addSpeed(50);
            new BukkitRunnable() {
                @Override
                public void run() {
                    int newTime = Utils.getIntFromItem(item, "speed");
                    if (newTime > 0)
                        Utils.setIntInItem(item, "speed", (newTime - 1));
                    if (newTime == 0) {
                        sbPlayer.removeSpeed(50);
                        cancel();
                    }
                }
            }.runTaskTimer(SkyBlock.getInstance(), 1, 20);
        }
    }
}
