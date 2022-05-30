package me.thomas.skyblock.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Storage {

	private static Inventory INV;

	public static void register() {
		Inventory inv = Bukkit.createInventory(null, 54, "Storage");

		setInventory(inv);
	}

	public Inventory getInventory() {
		return INV;
	}

	public static void setInventory(Inventory inv) {
		INV = inv;
	}

	public static void openInventory(Player player) {
		player.openInventory(INV);
	}
}
