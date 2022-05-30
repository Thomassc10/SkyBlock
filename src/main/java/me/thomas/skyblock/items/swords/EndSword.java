package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class EndSword extends SbItem implements Listener {

    public EndSword() {
        super(new ItemStack(Material.DIAMOND_SWORD), "End Sword", 35, 0, Arrays.asList("&7Deals &a+100% &7damage to",
                "&7Endermites, Ender", "&7Dragons and Enderman."), null, true, SbRarity.UNCOMMON_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (Utils.isEnd(event.getEntity())) {
            event.setDamage(event.getDamage() * 2);
        }
    }
}
