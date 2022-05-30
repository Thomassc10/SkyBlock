package me.thomas.skyblock.events;

import com.google.common.util.concurrent.AtomicDouble;
import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import me.thomas.skyblock.scoreboard.ScoreBoards;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutChat;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

	int taskID = 0;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.getInventory().setItem(8, SkyBlock.menuItem());

		SbPlayer sbPlayer = new SbPlayer(player.getUniqueId());
		sbPlayer.setMaxHealth(100);
		sbPlayer.setSpeed(100);
		sbPlayer.setCriticalChance(30);
		sbPlayer.setCriticalDamage(50);
		sbPlayer.setMaxMana(100);
		PlayerManager playerManager = PlayerManager.getPlayerManager();
		playerManager.addSBPlayer(sbPlayer);
		sbPlayer.setMana(sbPlayer.getMaxMana() - 1);

		ScoreBoards.setScoreboard(player);
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(sbPlayer.getMaxHealth());
		player.setHealthScale(40);
		player.setHealthScaled(true);
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.getInstance(), () -> {

			PlayerConnection playerConnection = ((CraftPlayer)player).getHandle().b;

			String firstText = ChatColor.RED + "❤" + (int) Math.round(player.getHealth()) + "/" + sbPlayer.getMaxHealth();
			String secondText = ChatColor.GREEN + "❈" + sbPlayer.getDefense();
			String thirdText = ChatColor.AQUA + "✎" + sbPlayer.getMana() + "/" + sbPlayer.getMaxMana();

			String text = firstText + "            " + secondText + "            " + thirdText;

			/*ClientboundChatPacket chatPacket = new ClientboundChatPacket(
					Component.Serializer.fromJson("{\"text\":\"" + text + "\"}"),
					ChatType.GAME_INFO, player.getUniqueId());*/
			PacketPlayOutChat packet = new PacketPlayOutChat(
					IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"),
					ChatMessageType.c, player.getUniqueId());
			playerConnection.sendPacket(packet);
			//playerConnection.send(chatPacket);
		}, 1, 10);
		sbPlayer.setTaskID(taskID);
		taskID++;
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(event.getPlayer());
		Bukkit.getScheduler().cancelTask(sbPlayer.getTaskID());
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getCurrentItem() == null) return;
		if (event.getCurrentItem().isSimilar(SkyBlock.menuItem()) && event.getSlot() == 8)
			event.setCancelled(true);
	}

	@EventHandler
	public void onRegen(EntityRegainHealthEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
}
