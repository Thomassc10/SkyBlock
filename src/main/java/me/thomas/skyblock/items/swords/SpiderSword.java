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

public class SpiderSword extends SbItem implements Listener {

    public SpiderSword() {
        super(new ItemStack(Material.IRON_SWORD), "Spider Sword", 30, 0, Arrays.asList("&7Deals &a+100% &7damage to",
                "&7Silverfish, Spiders and", "&7CaveSpiders."), null, true, SbRarity.COMMON_SWORD);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (Utils.isRightItem(event, getItem())) return;
        if (Utils.isSpider(event.getEntity()))
            event.setDamage(event.getDamage() * 2);
    }
}
