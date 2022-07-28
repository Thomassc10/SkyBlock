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

public class VoidedgeKatana extends me.thomas.skyblock.items.SbItem implements Listener {

    public VoidedgeKatana() {
        super(new ItemStack(Material.DIAMOND_SWORD), "Voidedge Katana", 125, 60, 0, 20,
                50, 0, 0, Arrays.asList("&7Deal &a+250% &7damage to Enderman.",
                        "&7Receive &a6% &7less damage", "&7from Enderman when held."), Collections.singletonList(
                                new SbAbility("Soulcry", AbilityType.RIGHT_CLICK, Arrays.asList("&7Gain &c+200⫽ Ferocity &7against",
                                        "&7Enderman for &a4s", "&0Soulflow Cost: &31"), 200, 4)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        item.setType(Material.GOLDEN_SWORD);
        event.getSbPlayer().addFerocity(200);
        Utils.scheduleTask(() -> {
            event.getSbPlayer().removeFerocity(200);
            item.setType(Material.DIAMOND_SWORD);
        }, 80);
    }

    @EventHandler
    public void onHitReceive(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        if (event.getDamager().getType() == EntityType.ENDERMAN) {
            if (Utils.getStringFromItem(player.getInventory().getItemInMainHand(), "item_key").equals("voidedge_katana"))
                event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 6));
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.ENDERMAN)
            event.setDamage(event.getDamage() * 3.5);
    }
}
