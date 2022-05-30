package me.thomas.skyblock.items;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class SbArmor {

    private final ItemStack item;
    private final String name;
    private final int health;
    private final int defense;
    private int strenght;
    private int critChance;
    private int critDamage;
    private int mana;
    private int speed;
    private double seaCreatureChance;
    private List<String> description;
    private List<SbAbility> abilities;
    private final boolean canBeReforged;
    private final SbRarity sbRarity;
    public SbArmor(ItemStack item, String name, int health, int defense, int strenght, int critChance, int critDamage, int mana, int speed, double seaCreatureChance, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.strenght = strenght;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.mana = mana;
        this.speed = speed;
        this.seaCreatureChance = seaCreatureChance;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbArmor(ItemStack item, String name, int health, int defense, int strenght, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.strenght = strenght;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbArmor(ItemStack item, String name, int health, int defense, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbArmor(ItemStack item, String name, int health, int defense, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public void setLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(sbRarity.getColor() + name);
        if (strenght > 0) {
            lore.add(ChatColor.GRAY + "Strenght: " + ChatColor.RED + "+" + strenght);
        }
        if (critChance > 0) {
            lore.add(ChatColor.GRAY + "Critical Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if (critDamage > 0) {
            lore.add(ChatColor.GRAY + "Critical Damage: " + ChatColor.RED + "+" + critDamage + "%");
        }
        if (seaCreatureChance > 0) {
            lore.add(Utils.color("&7Sea Creature Chance: &c+" + seaCreatureChance + "%"));
        }
        if (health > 0) {
            lore.add(ChatColor.GRAY + "Health: " + ChatColor.GREEN + "+" + health);
        }
        if (defense > 0) {
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        }
        if (mana > 0) {
            lore.add(Utils.color("&7Intelligence: &a+" + mana));
        }
        if (speed > 0) {
            lore.add(Utils.color("&7Speed: &a+" + speed));
        }
        lore.add("");
        if (description != null) {
            for (String d : description)
                lore.add(Utils.color(Utils.icon(d)));
            lore.add("");
        }
        if (abilities != null) {
            for (SbAbility sbAbility : abilities)
                lore.addAll(sbAbility.toLore());
            lore.add("");
        }
        if (canBeReforged)
            lore.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        lore.add(sbRarity.getColor() + sbRarity.toString().replace("_", " "));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(SkyBlock.getInstance(), "item_key");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ChatColor.stripColor(meta.getDisplayName().toLowerCase().replace(" ", "_")));
        item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getCritDamage() {
        return critDamage;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<SbAbility> getAbilities() {
        return abilities;
    }

    public boolean isCanBeReforged() {
        return canBeReforged;
    }

    public SbRarity getSbRarity() {
        return sbRarity;
    }

    public int getMana() {
        return mana;
    }

    public int getSpeed() {
        return speed;
    }
}
