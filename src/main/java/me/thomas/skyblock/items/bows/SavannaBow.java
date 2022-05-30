package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SavannaBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public SavannaBow() {
        super(new ItemStack(Material.BOW), "Savanna Bow", 50, 0, Arrays.asList("&7All damage dealt with this bow",
                "&7is &adoubled&7."), null, true, SbRarity.COMMON_BOW);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        event.setDamage(event.getDamage() * 2);
    }
}
