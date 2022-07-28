package me.thomas.skyblock.items.powerorbs;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class RadiantPowerOrb extends me.thomas.skyblock.items.SbItem implements Listener {

    public RadiantPowerOrb() {
        super(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0="),
                "Radiant Power Orb", Arrays.asList("&aOrb Buff: Radiant", "&7Heals &c1% &7of max &c%health% &7per second."), Collections.singletonList(new SbAbility("Deploy", AbilityType.NONE,
                        Arrays.asList("&7Place and orb for &a30s &7buffing", "&7up to &b5 &7players within &a18", "&7blocks.",
                                "&8Costs 50% of max mana.", "&8Only one deployable buff applies."))), false, SbRarity.UNCOMMON_DEPLOYABLE);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        SbPlayer sbPlayer = event.getSbPlayer();
        if (sbPlayer.getMana() < (sbPlayer.getMaxMana() / 2)) return;

        ArmorStand as = player.getWorld().spawn(player.getLocation().clone().subtract(0, 0.25, 0), ArmorStand.class);
        as.setInvisible(true);
        as.setInvulnerable(true);
        as.setCanPickupItems(false);
        as.setArms(false);
        as.setBasePlate(false);
        as.setGravity(false);
        as.setRemoveWhenFarAway(false);
        as.getEquipment().setHelmet(this.getItem());
        as.setMarker(true);

        sbPlayer.removeMana(sbPlayer.getMaxMana() / 2);

        new BukkitRunnable() {
            int i = 0;
            int time = 0;
            int regen = 0;
            @Override
            public void run() {
                as.setRotation(i, i);
                i+=5;
                if (time == 600) {
                    cancel();
                    as.remove();
                }
                if (regen == 20) {
                    for (Player p : Utils.getNearestPlayers(as, 18)) {
                        SbPlayer sbp = PlayerManager.getPlayerManager().getSBPlayer(p);
                        sbp.regenHealth(Utils.getPercent(sbp.getMaxHealth(), 1));
                    }
                    regen = 0;
                }
                regen++;
                time++;
            }

        }.runTaskTimer(SkyBlock.getPlugin(SkyBlock.class), 1, 1);
    }
}
