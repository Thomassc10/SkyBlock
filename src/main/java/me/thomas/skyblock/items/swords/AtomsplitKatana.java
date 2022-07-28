package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class AtomsplitKatana extends me.thomas.skyblock.items.SbItem implements Listener {

    public AtomsplitKatana() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Atomsplit Katana", 245, 100, 0, 30,
                300, 0, 0, Arrays.asList("&7Deal &a+450% &7damage to Enderman.",
                        "&7Receive &a12% &7less damage", "&7from Enderman when held."), Collections.singletonList(
                        new SbAbility("Soulcry", AbilityType.RIGHT_CLICK, Arrays.asList("&7Gain &c+400⫽ Ferocity &7against",
                                "&7Enderman for &a4s", "&8Soulflow Cost: &31"), 200, 4)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        item.setType(Material.GOLDEN_SWORD);
        event.getSbPlayer().addFerocity(400);
        Utils.scheduleTask(() -> {
            event.getSbPlayer().removeFerocity(400);
            item.setType(Material.DIAMOND_SWORD);
        }, 80);
    }

    @EventHandler
    public void onHitReceive(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        if (event.getDamager().getType() == EntityType.ENDERMAN) {
            if (Utils.getStringFromItem(player.getInventory().getItemInMainHand(), "item_key").equals("vorpal_katana"))
                event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 12));
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.ENDERMAN)
            event.setDamage(event.getDamage() * 5.5);
    }
}
