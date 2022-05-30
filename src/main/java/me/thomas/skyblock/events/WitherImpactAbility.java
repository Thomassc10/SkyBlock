package me.thomas.skyblock.events;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WitherImpactAbility implements Listener {

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        if (!event.getAbility().getName().contains("Wither impact")) return;
        Player player = event.getPlayer();
        SbPlayer sbPlayer = event.getSbPlayer();
        Block block = player.getTargetBlock(null, 10);
        player.teleport(block.getLocation());
        player.setAbsorptionAmount(Utils.getPercent(sbPlayer.getCriticalDamage(), 150));
        Utils.setIntInEntity(player, "wither_shield", 1);
        Utils.scheduleTask(() -> {
            double absorption = player.getAbsorptionAmount();
            sbPlayer.regenHealth(absorption/2);
            player.setAbsorptionAmount(0);

            Utils.setIntInEntity(player, "wither_shield", 0);
        }, 100);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (Utils.getIntFromEntity(event.getEntity(), "wither_shield") == 1)
            event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 10));
    }
}
