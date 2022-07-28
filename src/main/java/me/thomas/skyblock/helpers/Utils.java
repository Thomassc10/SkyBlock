package me.thomas.skyblock.helpers;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.UseAbility;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static boolean isUndead(Entity entity) {
        return entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.SKELETON ||
                entity.getType() == EntityType.PIGLIN || entity.getType() == EntityType.WITHER ||
                entity.getType() == EntityType.WITHER_SKELETON;
    }

    public static boolean isSpider(Entity entity) {
        return entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.SILVERFISH || entity.getType() == EntityType.CAVE_SPIDER;
    }

    public static boolean isEnd(Entity entity) {
        return entity.getType() == EntityType.ENDERMITE || entity.getType() == EntityType.ENDER_DRAGON || entity.getType() == EntityType.ENDERMAN;
    }

    public static void setStringInItem(ItemStack item, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
    }

    public static String getStringFromItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
        return "";
    }

    public static void setIntInItem(ItemStack item, String key, int value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
        item.setItemMeta(meta);
    }

    public static int getIntFromItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (item != null && item.getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.INTEGER))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER);
        return 0;
    }

    public static void setArrayInItem(ItemStack item, String key, long[] value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.LONG_ARRAY, value);
        item.setItemMeta(meta);
    }

    public static long[] getArrayInItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (item != null && item.getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.LONG_ARRAY))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.LONG_ARRAY);
        return new long[]{};
    }

    public static void setIntInEntity(Entity entity, String key, int value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        entity.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
    }

    public static int getIntFromEntity(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (entity != null && entity.getPersistentDataContainer().has(namespacedKey, PersistentDataType.INTEGER))
            return entity.getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER);
        return 0;
    }

    public static void setStringInEntity(Entity entity, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        entity.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
    }

    public static String getStringFromEntity(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (entity != null && entity.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING))
            return entity.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
        return "";
    }

    public static void setItemInEntity(Entity entity, String key, ItemStack value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        entity.getPersistentDataContainer().set(namespacedKey, new StoreItemStack(), value);
    }

    public static ItemStack getItemFromEntity(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        return entity.getPersistentDataContainer().get(namespacedKey, new StoreItemStack());
    }

    public static boolean isSbItem(ItemStack item) {
        return item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SkyBlock.getInstance(), "item_key"), PersistentDataType.STRING);
    }

    public static boolean isSbArmor(ItemStack item) {
        String key = getStringFromItem(item, "item_key");
        for (String i : Items.getSbArmors().keySet())
            if (i.equals(key))
                return true;
        return false;
    }

    public static boolean isSbEntity(Entity entity) {
        return getStringFromEntity(entity, "entity_key") != null;
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String icon(String icon) {
        Placeholder placeholder = new Placeholder();
        return placeholder.replaceLines(icon);
    }

    public static void scheduleTask(Runnable runnable, int i) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.getInstance(), runnable, i);
    }

    public static void addEnchantmentGlint(ItemStack item) {
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }

    public static ItemStack dyeArmor(ItemStack item, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;
    }

    public static List<LivingEntity> getNearestEntities(Entity entity, double distance) {
        List<Entity> entities = entity.getNearbyEntities(distance, distance, distance);
        List<LivingEntity> living = new ArrayList<>();
        for (Entity e : entities)
            if (e instanceof LivingEntity && !(e instanceof Player))
                living.add((LivingEntity) e);
            return living;
    }

    public static LivingEntity getNearestEntity(Entity entity, double distance) {
        List<Entity> entities = entity.getNearbyEntities(distance, distance, distance);
        List<LivingEntity> livings = new ArrayList<>();
        LivingEntity lv = null;
        if (entities.isEmpty()) return null;
        for (Entity e : entities)
            if (e instanceof LivingEntity && !(e instanceof Player))
                livings.add((LivingEntity) e);
            if (livings.isEmpty()) return null;
            lv = livings.get(0);
        return lv;
    }

    public static List<Player> getNearestPlayers(Entity entity, int distance) {
        List<Entity> entities = entity.getNearbyEntities(distance, 2, distance);
        List<Player> living = new ArrayList<>();
        for (Entity e : entities)
            if (e instanceof Player)
                living.add((Player) e);
        return living;
    }

    public static boolean isRightItem(EntityDamageByEntityEvent event, ItemStack item) {
        if (event.getDamager() instanceof Player) {
            Player player = (((Player) event.getDamager()).getPlayer());
            ItemStack playerItem = player.getInventory().getItemInMainHand();
            NamespacedKey key = new NamespacedKey(SkyBlock.getInstance(), "item_key");
            return playerItem != null && item.hasItemMeta() && playerItem.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING) && playerItem.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING) && playerItem.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING).equals(item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }
        return false;
    }

    public static boolean isRightItem(Player player, ItemStack item) {
        NamespacedKey key = new NamespacedKey(SkyBlock.getInstance(), "item_key");
        ItemStack playerItem = player.getInventory().getItemInMainHand();
        return playerItem != null && item.hasItemMeta() && playerItem.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING) && playerItem.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING) && playerItem.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING).equals(item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
    }

    public static boolean isRightItem(PlayerItemHeldEvent event, ItemStack item) {
        NamespacedKey key = new NamespacedKey(SkyBlock.getInstance(), "item_key");
        ItemStack playerItem = event.getPlayer().getInventory().getItem(event.getNewSlot());
        return playerItem != null && item.hasItemMeta() && playerItem.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING) && playerItem.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING) && playerItem.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING).equals(item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
    }

    public static boolean isRightClick(Action action) {
        return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }

    public static double getPercent(double number, int percentage) {
        return (percentage * number) / 100;
    }

    public static ItemStack getValueHead(String value) {
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(new ItemStack(Material.PLAYER_HEAD, 1, (short) 3),
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }

    public static void spawnCircle(double size, Player player, Particle particle) {
        for (int i = 0; i < 360; i++) {
            double angle = (i * Math.PI / 180);
            double x = size * Math.cos(angle);
            double z = size * Math.sin(angle);
            Location loc = player.getLocation().add(x, 1, z);
            player.spawnParticle(particle, loc, 1);
        }
    }

    public static void changeLore(ItemStack item, String loreText, String value) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        int line = 0;
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains(loreText))
                line = i;
        }
        lore.set(line, color(value));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static int getLoreStat(ItemStack item, StatType type) {
        ItemMeta meta = item.getItemMeta();
        for (String lore : meta.getLore()) {
            if (lore.contains(type.getText())) {
                String[] values = lore.split(" +");
                String s = values.length == 2 ? values[1] : values[2].replace("%", "");
                return Integer.parseInt(ChatColor.stripColor(s));
            }
        }
        return 0;
    }

    public static void playSound(Player player, ActionSound sound) {
        switch (sound) {
            case OPEN: player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 1);
                break;
            case MODIFY: player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 0.5f, 1);
                break;
            case SELECT: player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                break;
            case CLICK: player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.5f, 1);
                break;
            case POP: player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 0.5f, 1);
                break;
            case BREAK: player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1);
                break;
            case ERROR: player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1);
                break;
            default:
                break;
        }
    }

    public static boolean isItemActive(Player player, ItemStack item) {
        for (ItemStack i : player.getInventory().getContents()) {
            if (getStringFromItem(i, "item_key").equals(getStringFromItem(item, "item_key")))
                if (getIntFromItem(i, "active") == 1)
                    return true;
        }
        return false;
    }

    public static double getMeleeDamage(SbPlayer sbPlayer, SbItem sbItem, boolean crit) {
        int damage = sbItem != null ? sbItem.getDamage() : 0;
        double baseDamage = (5 + damage) /*+ (stats.getAllStrenght(player, item) / 5))*/ * (1 + (double) (sbPlayer.getStrenght() / 100));
        double damageMultiplier = 1 + (sbPlayer.getSkills().getCombatSkill().getLevel() * 0.04) /*enchants + weapon bonus*/;
        double finalDamage = (baseDamage * damageMultiplier) /*armor bonus*/;
        Random r = new Random();
        if (!crit) {
            if (r.nextInt(100) <= sbPlayer.getCriticalChance()) {
                finalDamage *= (1 + ((double) sbPlayer.getCriticalDamage() / 100));
            }
        } else finalDamage *= (1 + ((double) sbPlayer.getCriticalDamage() / 100));
        return finalDamage;
    }

    public static void resetCooldown(Player player, SbAbility ability) {
        UseAbility useAbility = new UseAbility();
        useAbility.cooldowns.get(player).remove(ability.getName());
    }

    public static void updateScoreboardPrefix(Player player, String team, String prefix) {
        player.getScoreboard().getTeam(team).setPrefix(prefix);
    }

    public static void updateScoreboardSuffix(Player player, String team, String suffix) {
        player.getScoreboard().getTeam(team).setSuffix(suffix);
    }
}
