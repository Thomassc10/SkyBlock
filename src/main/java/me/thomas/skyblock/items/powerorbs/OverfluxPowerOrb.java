package me.thomas.skyblock.items.powerorbs;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class OverfluxPowerOrb extends SbItem implements Listener {

	public OverfluxPowerOrb() {
		super(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0="),
				"Overflux Power Orb", Arrays.asList("&5Orb Buff: Overflux", "&7Grants &b+100% &7base mana regen.",
						"&7Heals &c2.5% &7of max &c❤ &7per second.", "&7Increases all heals by &a+5%.", "&7Grants &c+25❁Strength."),
				Collections.singletonList(new SbAbility("Deploy", AbilityType.NONE,
						Arrays.asList("&7Place an orb for &a60s &7buffing", "&7up to &b5 &7players within &a20", "&7blocks.",
								"&8Costs 50% of max mana.", "&8Only one deployable buff applies."))), false, SbRarity.LEGENDARY_DEPLOYABLE);
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
		sbPlayer.addStrenght(25);

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
					sbPlayer.removeStrenght(25);
				}
				if (regen == 20) {
					for (Player p : Utils.getNearestPlayers(as, 18)) {
						SbPlayer sbp = PlayerManager.getPlayerManager().getSBPlayer(p);
						sbp.regenHealth(Utils.getPercent(sbp.getMaxHealth(), (int) 2.5));
						sbp.regenMana(sbp.getMaxMana() * 0.02);
					}
					regen = 0;
				}
				regen++;
				time++;
			}

		}.runTaskTimer(SkyBlock.getPlugin(SkyBlock.class), 1, 1);
	}
}