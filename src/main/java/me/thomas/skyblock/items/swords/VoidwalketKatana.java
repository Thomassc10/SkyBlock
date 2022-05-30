package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class VoidwalketKatana extends SbItem implements Listener {

    public VoidwalketKatana() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Voidwalker Katana", 80, 40, 0, 10, 0, 0, 0,
                Arrays.asList("&7Deal &a+150% &7damage to Enderman.", "&7Receive &a3% &7 less damage",
                        "&7from Enderman when held."), null, true, SbRarity.UNCOMMON_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.ENDERMAN)
            event.setDamage(event.getDamage() * 2.5);
    }

    @EventHandler
    public void onHitReceive(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        if (event.getDamager().getType() == EntityType.ENDERMAN) {
            if (Utils.getStringFromItem(player.getInventory().getItemInMainHand(), "item_key").equals("voidwalker_katana"))
                event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 3));
        }
    }
}
