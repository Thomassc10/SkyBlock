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

public class PlasmafluxPowerOrb extends me.thomas.skyblock.items.SbItem implements Listener {

    public PlasmafluxPowerOrb() {
        super(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZDRjZTIzOTMzZTY2ZTA0ZGYxNjA3MDY0NGY3NTk5ZWViNTUzMDdmN2VhZmU4ZDkyZjQwZmIzNTIwODYzYyJ9fX0="),
                "Plasmaflux Power Orb", Arrays.asList("&aOrb Buff: Plasmaflux", "&7Grants &b+125% &7base mana regen.",
                        "Heals &c3% &7of max &c❤ &7per second.", "&7Increases all heals by &a+7.5%.", "&7Grants &c+35❁Strength."),
                Collections.singletonList(new SbAbility("Deploy", AbilityType.NONE,
                        Arrays.asList("&7Place an orb for &a60s &7buffing", "up to &b5 &7players within &a20", "&7blocks.",
                                "&8Costs 50% of max mana.", "&8Only one deployable buff applies."))), false, SbRarity.EPIC_POWER_ORB);
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
        sbPlayer.addStrenght(35);

        new BukkitRunnable() {
            int i = 0;
            int time = 0;
            int regen = 0;
            @Override
            public void run() {
                as.setRotation(i, i);
                i+=5;
                if (time == 1200) {
                    cancel();
                    as.remove();
                    sbPlayer.removeStrenght(35);
                }
                if (regen == 20) {
                    for (Player p : Utils.getNearestPlayers(as, 20)) {
                        SbPlayer sbp = PlayerManager.getPlayerManager().getSBPlayer(p);
                        sbp.regenHealth(Utils.getPercent(sbp.getMaxHealth(), 3));
                        sbp.regenMana(sbp.getMaxMana() * 0.02 * 1.25);
                    }
                    regen = 0;
                }
                regen++;
                time++;
            }

        }.runTaskTimer(SkyBlock.getPlugin(SkyBlock.class), 1, 1);
    }
}
