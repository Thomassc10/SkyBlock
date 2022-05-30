package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class RaiderAxe extends me.thomas.skyblock.items.SbItem implements Listener {

    public RaiderAxe() {
        super(new ItemStack(Material.IRON_AXE), "Raider Axe", 80, 50,
                Arrays.asList("&7Earn &6+20 coins &7form monster kills (level &e+10 &7only)", "",
                        "&c+1 Damage &7per &c500 &7kills", "&0Max +35", "&7Kills: &c0", "",
                        "&c+1 ❁ Strenght &7per &e500 &7wood", "&0Sums wood collections, max +100",
                        "&7Wood collections: &e0"), null, true, SbRarity.RARE_SWORD);
        Utils.setIntInItem(getItem(), "kills", 0);
        Utils.setIntInItem(getItem(), "damage", 0);
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (!Utils.isRightItem(player, getItem())) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        int kills = Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "kills");
        Utils.changeLore(item, "Kills: ", "&7Kills: " + (kills + 1));
        SbItem sbItem = Items.getSbItem(item);
        if (kills%500 == 0) {
            sbItem.addDamage(1);
            Utils.setIntInItem(item, "damage", (Utils.getIntFromItem(item, "damage") + 1));
        }
    }
}
