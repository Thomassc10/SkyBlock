package me.thomas.skyblock.menus;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;

public class SbMenu {

    private static Inventory INV;

    public static void register() {
        Inventory inv = Bukkit.createInventory(null, 54, "SkyBlock Menu");
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        // Skills
        meta.setDisplayName(ChatColor.GREEN + "Your Skills");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "View your skill progression", "and rewards.", "", ChatColor.YELLOW + "Click to view!"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inv.setItem(19, item);

        // Collections
        item.setType(Material.PAINTING);
        meta.setDisplayName(ChatColor.GREEN + "Collection");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "View all of the items available", "in SkyBlock. Collect more of an", "item to unlock rewards on your", "way to becoming a master of", "SkyBlock!", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(20, item);

        // Recipes
        item.setType(Material.BOOK);
        meta.setDisplayName(ChatColor.GREEN + "Recipe Book");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Through your adventure you will", "unlock recipes for all kinds of", "special items! You can view how", "craft these items here.", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(21, item);

        // Trades
        item.setType(Material.EMERALD);
        meta.setDisplayName(ChatColor.GREEN + "Trades");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "View your available trades.", "These trades are always", "available and accessible", "through the SkyBlock menu.", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(22, item);

        // Quests
        item.setType(Material.WRITABLE_BOOK);
        meta.setDisplayName(ChatColor.GREEN + "Quest Log");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "View you active quests", "progress and rewards.", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(23, item);

        // Events
        item.setType(Material.CLOCK);
        meta.setDisplayName(ChatColor.GREEN + "Calendar and Events");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "View the SkyBlock Calendar", "upcoming events and event", "rewards.", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(24, item);

        // Storage
        item.setType(Material.CHEST);
        meta.setDisplayName(ChatColor.GREEN + "Storage");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Store global items that you", "want to access at any time", "from anywhere here.", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(25, item);

        // pets
        item.setType(Material.BONE);
        meta.setDisplayName(ChatColor.GREEN + "Pets");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "View and manage all of your", "Pets.", "", "Level up your pets faster by", "gaining xp in their favorite", "skill!", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(30, item);

        // WorkBench
        item.setType(Material.CRAFTING_TABLE);
        meta.setDisplayName(ChatColor.GREEN + "Crafting Table");
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "Opens the crafting grid."));
        item.setItemMeta(meta);
        inv.setItem(31, item);

        // Wardrobe
        item.setType(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();
        armorMeta.setColor(Color.PURPLE);
        meta.setDisplayName(ChatColor.GREEN + "Wardrobe");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Stores armor sets and quickly", "swaps between them!", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(32, item);

        ItemStack bank = Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM2ZTk0ZjZjMzRhMzU0NjVmY2U0YTkwZjJlMjU5NzYzODllYjk3MDlhMTIyNzM1NzRmZjcwZmQ0ZGFhNjg1MiJ9fX0=");
        ItemMeta bankMeta = bank.getItemMeta();
        bankMeta.setDisplayName(ChatColor.GREEN + "Personal Bank");
        bankMeta.setLore(Arrays.asList(ChatColor.GRAY + "Contact your Banker from", "anywhere.", "", ChatColor.YELLOW + "Click to view!"));

        ItemStack earth = Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljODg4MWU0MjkxNWE5ZDI5YmI2MWExNmZiMjZkMDU5OTEzMjA0ZDI2NWRmNWI0MzliM2Q3OTJhY2Q1NiJ9fX0=");
        ItemMeta earthMeta = earth.getItemMeta();
        earthMeta.setDisplayName(ChatColor.AQUA + "Fast Travel");
        earthMeta.setLore(Arrays.asList(ChatColor.GRAY + "Teleport to islands you've", "already visited.", "", ChatColor.YELLOW + "Click to view locations!"));
        earth.setItemMeta(earthMeta);
        inv.setItem(47, earth);

        ItemStack profiles = new ItemStack(Material.NAME_TAG);
        ItemMeta profilesMeta = profiles.getItemMeta();
        profilesMeta.setDisplayName(ChatColor.GREEN + "Profile Management");
        profilesMeta.setLore(Arrays.asList(ChatColor.GRAY + "You can have multiple SkyBlock", "profiles at the same time.", "", "Each profile has its own island, inventory, quest log..."));
        profiles.setItemMeta(profilesMeta);
        inv.setItem(48, profiles);

        // Cookie
        item.setType(Material.COOKIE);
        meta.setDisplayName(ChatColor.GOLD + "Booster Cookie");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Obtain the " + ChatColor.LIGHT_PURPLE + "Cookie buff", ChatColor.GRAY + "from booster cookies in the", "hub's community shop.", "", ChatColor.YELLOW + "Click to view!"));
        item.setItemMeta(meta);
        inv.setItem(49, item);

        // rest
        for (int i = 0; i < 54; i++){
            if (inv.getItem(i) == null)
                inv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        setInventory(inv);
    }

    public static Inventory getInventory() {
        return INV;
    }

    public static void setInventory(Inventory inventory) {
        INV = inventory;
    }

    public static void openInventory(Player player) {
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setOwningPlayer(player);
        headMeta.setDisplayName(ChatColor.GREEN + "Your SkyBlock Profile");
        headMeta.setLore(Arrays.asList(
                ChatColor.RED + "❤ Health " + ChatColor.WHITE + sbPlayer.getMaxHealth() + " HP",
                ChatColor.GREEN + "❈ Defense " + ChatColor.WHITE + sbPlayer.getDefense(),
                ChatColor.RED + "❁ Strenght " + ChatColor.WHITE + sbPlayer.getStrenght(),
                ChatColor.WHITE + "✦ Speed " + sbPlayer.getSpeed(),
                ChatColor.BLUE + "☣ Crit Chance " + ChatColor.WHITE + sbPlayer.getCriticalChance() + "%",
                ChatColor.BLUE + "☠ Crit Damage " + ChatColor.WHITE + sbPlayer.getCriticalDamage() + "%",
                ChatColor.AQUA + "✎ Intelligence " + ChatColor.WHITE + sbPlayer.getMaxMana(),
                ChatColor.GOLD + "⸕ Mining Speed " + ChatColor.WHITE + 0,
                ChatColor.YELLOW + "⚔ Bonus Attack Speed " + ChatColor.WHITE + 0,
                ChatColor.DARK_AQUA + "α Sea Creature Chance " + ChatColor.WHITE + 20 + "%",
                ChatColor.AQUA + "✯ Magic Find " + ChatColor.WHITE + 5,
                ChatColor.LIGHT_PURPLE + "♣ Pet Luck " + ChatColor.WHITE + 0,
                ChatColor.WHITE + "❂ True Defense " + ChatColor.WHITE + 0,
                ChatColor.RED + "⫽ Ferocity " + ChatColor.WHITE + 0,
                ChatColor.RED + "๑ Ability Damage " + ChatColor.WHITE + 0 + "%",
                ChatColor.GOLD + "☘ Mining Fortune " + ChatColor.WHITE + 0,
                ChatColor.GOLD + "☘ Farming Fortune " + ChatColor.WHITE + 0,
                ChatColor.GOLD + "☘ Foraging Fortune " + ChatColor.WHITE + 0,
                ChatColor.DARK_PURPLE + "✧ Pristine " + ChatColor.WHITE + 0,
                "", ChatColor.YELLOW + "Click to view!"));
        head.setItemMeta(headMeta);
        INV.setItem(13, head);
        player.openInventory(INV);
    }
}
