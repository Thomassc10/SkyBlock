package me.thomas.skyblock.events;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerDamageDeal implements Listener {

	ChatColor[] colors = {ChatColor.WHITE, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.RED};
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onHit(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getDamager();
		if (!(event.getEntity() instanceof LivingEntity)) return;
		SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);

		ItemStack item = player.getInventory().getItemInMainHand();
		SbItem sbItem = Items.getSbItem(item);
		if (sbItem.getSbRarity().toString().contains("BOW") && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
		//EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
		int damage = sbItem != null ? sbItem.getDamage() : 0;
														/*old strenght*/
		double baseDamage = (5 + damage) /*+ (stats.getAllStrenght(player, item) / 5))*/ * (1 + (double) (sbPlayer.getStrenght() / 100));
		double damageMultiplier = 1 + (sbPlayer.getSkills().getCombatSkill().getLevel() * 0.04) /*enchants + weapon bonus*/;
		double finalDamage = (int) Math.round(baseDamage * damageMultiplier) /*armor bonus*/;

		String name = "" + ChatColor.GRAY + finalDamage;
		Random r = new Random();
		if (r.nextInt(100) <= sbPlayer.getCriticalChance()) {
			finalDamage *= (int) Math.round(1 + ((double) sbPlayer.getCriticalDamage() / 100));
			String value = String.valueOf(finalDamage);
			String[] a = value.split("(?<=.)");
			List<String> numbers = new ArrayList<>(Arrays.asList(a));
			int color = 0;
			StringBuilder builder = new StringBuilder();
			builder.append(Utils.icon("%pristine%"));
			for (String number : numbers) {
				builder.append(colors[color]).append(number);
				if (color == 3)
					color = 0;
				color++;
			}
			builder.append(ChatColor.WHITE).append(Utils.icon("%pristine%"));
			name = builder.toString();
		}
		String finalName = name;
		ArmorStand armorStand = player.getWorld().spawn(event.getEntity().getLocation(), ArmorStand.class, a -> {
			a.setInvisible(true);
			a.setInvulnerable(true);
			a.setMarker(true);
			a.setCustomName(finalName);
			a.setCustomNameVisible(true);
		});
		event.setDamage(finalDamage);
		Utils.scheduleTask(armorStand::remove, 15);

		if (sbPlayer.getFerocity() > 0) {
			if (r.nextInt(100) <= sbPlayer.getFerocity()) {
				if (event.getEntity().isDead()) return;
				Utils.scheduleTask(() -> {
					Location loc = player.getEyeLocation();
					Vector v = new Vector(0, loc.getDirection().getY(), 0);
					for (double i = 0; i < 5; i++) {
						v.multiply(i);
						loc.clone().add(v);
						player.spawnParticle(Particle.REDSTONE, loc, 1);
						v.normalize();
					}
					player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1, 1);
					((LivingEntity) event.getEntity()).damage(event.getDamage());
					//world.addEntity(armorStand);
				}, 30);
			}
		}
	}
}
