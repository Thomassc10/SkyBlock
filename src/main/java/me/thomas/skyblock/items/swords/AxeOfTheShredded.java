package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class AxeOfTheShredded extends me.thomas.skyblock.items.SbItem implements Listener {

    public AxeOfTheShredded() {
        super(new ItemStack(Material.DIAMOND_AXE), "Axe Of The Shredded", 140, 115, Arrays.asList(
                "&7Heal &c50❤ &7per hit.", "&7Deal &a+250% &7damage to Zombies.", "&7Receive &a25% &7less damage",
                "&7from Zombies when held."), Collections.singletonList(new SbAbility("Throw", AbilityType.RIGHT_CLICK,
                Arrays.asList("&7Throw your axe damaging all", "&7enemies in its path dealing",
                        "&c10% &7melee damage.", "&7Consecutive throws stack &cx2",
                        "&7damage but costs &92x &7mana up", "&7to &916x&7."), 20)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {

    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        if (event.getEntity().getType() == EntityType.ZOMBIE)
            event.setDamage(event.getDamage() * 3.5);
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer((Player) event.getDamager());
        sbPlayer.regenHealth(50);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        if (Utils.getStringFromItem(player.getInventory().getItemInMainHand(), "item_key").equals("axe_of_the_shredded")) {
            event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 25));
        }
    }
}
