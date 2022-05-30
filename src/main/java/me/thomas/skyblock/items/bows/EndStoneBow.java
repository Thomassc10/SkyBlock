package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class EndStoneBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public EndStoneBow() {
        super(new ItemStack(Material.BOW), "End Stone Bow", 140, 0, null, Collections.singletonList(
                new SbAbility("Extreme Focus", AbilityType.LEFT_CLICK, Arrays.asList("&7Consumes all your mana, and your",
                        "&7next hit will deal that much", "&7more damage."))), true, SbRarity.EPIC_BOW);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        if (event.getType() != AbilityType.LEFT_CLICK) return;
        int mana = event.getSbPlayer().getMana();
        Utils.setIntInEntity(event.getPlayer(), "end_bow", mana);
        event.getSbPlayer().removeMana(mana);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        int i = Utils.getIntFromEntity(player, "end_bow");
        if (i > 0) {
            event.setDamage(event.getDamage() + i);
            Utils.setIntInEntity(player, "end_bow", 0);
        }
    }
}
