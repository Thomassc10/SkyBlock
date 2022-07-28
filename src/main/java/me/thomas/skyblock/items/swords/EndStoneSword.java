package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class EndStoneSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public EndStoneSword() {
        super(new ItemStack(Material.GOLDEN_SWORD), "End Stone Sword", 120, 80, null, Collections.singletonList(
                new SbAbility("Extreme Focus", AbilityType.RIGHT_CLICK, Arrays.asList("&7Consumes all your remaining mana",
                        "&7to grant Damage Resistance for", "&a5 &7seconds and extra damage",
                        "&7on your next hit (within 5", "&7seconds) depending on how much", "&7mana was consumed!"), 20)), true, SbRarity.EPIC_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        SbPlayer sbPlayer = event.getSbPlayer();
        int mana = sbPlayer.getMana();
        sbPlayer.removeMana(mana);
        sbPlayer.getPlayer().setInvulnerable(true);
        Utils.setIntInEntity(event.getPlayer(), "end_stone_sword", mana);
        Utils.scheduleTask(() -> {
            Utils.setIntInEntity(sbPlayer.getPlayer(), "end_stone_sword", 0);
            sbPlayer.getPlayer().setInvulnerable(false);
        }, 100);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        Player player = (Player) event.getDamager();
        if (Utils.getIntFromEntity(player, "end_stone_sword") > 0) {
            event.setDamage(event.getDamage() + Utils.getPercent(event.getDamage(), (Utils.getIntFromEntity(player, "end_stone_sword") / 10)));
            Utils.setIntInEntity(player, "end_stone_sword", 0);
        }
    }
}
