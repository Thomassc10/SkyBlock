package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class WitherBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public WitherBow() {
        super(new ItemStack(Material.BOW), "Wither Bow", 30, 0, Arrays.asList("&7Deals &a+100% &7damage to",
                "&aWithers and Wither", "&aSkeletons."), null, true, SbRarity.UNCOMMON_BOW);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.WITHER || event.getEntity().getType() == EntityType.WITHER_SKELETON) {
            event.setDamage(event.getDamage() * 2);
        }
    }
}
