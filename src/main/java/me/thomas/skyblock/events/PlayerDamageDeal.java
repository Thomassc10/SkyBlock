package me.thomas.skyblock.events;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
		EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
		armorStand.setInvisible(true);
		armorStand.setInvulnerable(true);
		armorStand.setMarker(true);
		int damage = sbItem != null ? sbItem.getDamage() : 0;
																			/*old strenght*/
		double baseDamage = (5 + damage) /*+ (stats.getAllStrenght(player, item) / 5))*/ * (1 + (double) (sbPlayer.getStrenght() / 100));
		double damageMultiplier = 1 + (sbPlayer.getSkills().getCombatSkill().getLevel() * 0.04) /*enchants + weapon bonus*/;
		double finalDamage = Math.round(baseDamage * damageMultiplier) /*armor bonus*/;

		String name = "" + ChatColor.GRAY + finalDamage;
		Random r = new Random();
		if (r.nextInt(100) <= sbPlayer.getCriticalChance()) {
			finalDamage *= (1 + ((double) sbPlayer.getCriticalDamage() / 100));
			String value = String.valueOf(finalDamage);
			String[] a = value.split("(?<=.)");
			List<String> numbers = new ArrayList<>(Arrays.asList(a));
			int color = 0;
			StringBuilder builder = new StringBuilder();
			for (String number : numbers) {
				builder.append(colors[color]).append(number);
				if (color == 3)
					color = 0;
				color++;
			}
			name = builder.toString();
		}

		armorStand.setCustomName(new ChatComponentText(name));
		armorStand.setCustomNameVisible(true);
		Location l = event.getEntity().getLocation();
		armorStand.setPosition(l.getX(), l.getY(), l.getZ());
		event.setDamage(finalDamage);
		WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
		world.addEntity(armorStand);
		Utils.scheduleTask(() -> armorStand.getBukkitEntity().remove(), 15);

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
				world.addEntity(armorStand);
			}, 30);
		}
	}

}
