package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class MagmaBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public MagmaBow() {
        super(new ItemStack(Material.BOW), "Magma Bow", 120, 120, Arrays.asList(
                "&7Consumes 1 &6Magma Cream &7from", "&7the Inventory or Quiver to",
                "&a2x &7damage per shot."), null, true, SbRarity.EPIC_BOW);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        Player player = (Player) event.getDamager();
        for (ItemStack i : player.getInventory().getContents()) {
            if (i.getType() == Material.MAGMA_CREAM) {
                ItemStack item = i.clone();
                item.setAmount(1);
                event.setDamage(event.getDamage() * 2);
                player.getInventory().remove(item);
            }
        }
    }
}
