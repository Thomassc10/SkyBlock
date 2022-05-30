package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ReaperFalchion extends me.thomas.skyblock.items.SbItem implements Listener {

    public ReaperFalchion() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Reaper Falchion", 120, 100, 0, 0, 200,
                0 ,0, Arrays.asList("&7Heal &c10❤ &7per hit.", "&7Deal &a+200% &7damage to Zombies.",
                        "&7Receive &a20% &7less damage", "&7from Zombies when held."), null, true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.ZOMBIE)
            event.setDamage(event.getDamage() * 3);
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer((Player) event.getDamager());
        sbPlayer.regenHealth(10);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        if (Utils.getStringFromItem(player.getInventory().getItemInMainHand(), "item_key").equals("reaper_falchion")) {
            event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 20));
        }
    }
}
