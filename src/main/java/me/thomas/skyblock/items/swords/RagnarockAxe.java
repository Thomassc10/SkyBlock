package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class RagnarockAxe extends me.thomas.skyblock.items.SbItem implements Listener {

    private int taskID;

    public RagnarockAxe() {
        super(new ItemStack(Material.GOLDEN_AXE), "Ragnarock Axe", 250, 70, null, Collections.singletonList(
                new SbAbility("Ragnarock", AbilityType.RIGHT_CLICK, Arrays.asList("&7Begin a channel.",
                        "&7After not taking damage for", "&a3s&7, gain &a2x &7this",
                        "&7weapon's strenght for &a3s&7."), 500, 20)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (!Utils.isRightClick(event.getAction())) return;
        Utils.setStringInEntity(player, "ragnarock", "true");
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.getInstance(), () -> {
            SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
            sbPlayer.addStrenght(getStrenght() * 2);
            Utils.scheduleTask(() -> sbPlayer.removeStrenght(getStrenght() * 2), 60);
        }, 60);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
         if (!(event.getEntity() instanceof Player)) return;
         Player player = ((Player) event.getEntity()).getPlayer();
        if (Utils.getStringFromEntity(player, "ragnarock").equals("true")) {
            Bukkit.getScheduler().cancelTask(taskID);
            player.sendMessage(ChatColor.RED + "Channel failed.");
        }
    }
}
