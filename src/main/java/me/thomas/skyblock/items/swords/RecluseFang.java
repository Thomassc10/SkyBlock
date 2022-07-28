package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class RecluseFang extends me.thomas.skyblock.items.SbItem implements Listener {

    public RecluseFang() {
        super(new ItemStack(Material.IRON_SWORD), "Recluse Fang", 120, 30, 0, 20, 0, 0, 0, null, Collections.singletonList(
                new SbAbility("Sqash'em", AbilityType.NONE, Arrays.asList("&7Squash Spiders to accumulate",
                        "&7strenght against them.", "&7Bonus: &c+0", "&8+1 strenght per 40 squashed"))), true, SbRarity.RARE_SWORD);
        Utils.setIntInItem(getItem(), "kills", 0);
        Utils.setIntInItem(getItem(), "strenght", 0);
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (!Utils.isRightItem(player, getItem())) return;
        if (!Utils.isSpider(event.getEntity())) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        int kills = Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "kills");
        Utils.changeLore(item, "Bonus: ", "&7Bonus: &c+" + (kills + 1));
        SbItem sbItem = Items.getSbItem(item);
        if (kills%40 == 0) {
            sbItem.addStrenght(1);
            Utils.setIntInItem(item, "strenght", (Utils.getIntFromItem(item, "strenght") + 1));
        }
    }
}
