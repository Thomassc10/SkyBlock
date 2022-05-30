package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class UndeadBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public UndeadBow() {
        super(new ItemStack(Material.BOW), "Undead Bow", 80, 0, Arrays.asList(
                "&7Deals &a+100% &7damage to", "&aUndead monsters."), null, true, SbRarity.RARE_BOW);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (Utils.isUndead(event.getEntity()))
            event.setDamage(event.getDamage() * 2);
    }
}
