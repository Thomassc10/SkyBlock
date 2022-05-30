package me.thomas.skyblock.items;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SbItem {

    private ItemStack item;
    private String name;
    private int swingRange;
    private int damage;
    private int strenght;
    private int critChance;
    private int critDamage;
    private int mana;
    private int speed;
    private int defense;
    private int ferocity;
    private List<String> description;
    private List<SbAbility> abilities;
    private boolean canBeReforged;
    private SbRarity sbRarity;
    public SbItem(ItemStack item, String name, int damage, int strenght, int critChance, int critDamage, int mana, int speed, int defense, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.damage = damage;
        this.strenght = strenght;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.mana = mana;
        this.speed = speed;
        this.defense = defense;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbItem(ItemStack item, String name, int damage, int strenght, int critChance, int critDamage, int mana, int speed, int defense, int ferocity, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.damage = damage;
        this.strenght = strenght;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.mana = mana;
        this.speed = speed;
        this.defense = defense;
        this.ferocity = ferocity;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbItem(ItemStack item, String name, int damage, int strenght, int critChance, int critDamage, int mana, int speed, int defense, int ferocity, int swingRange, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.swingRange = swingRange;
        this.damage = damage;
        this.strenght = strenght;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.mana = mana;
        this.speed = speed;
        this.defense = defense;
        this.ferocity = ferocity;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbItem(ItemStack item, String name, int damage, int strenght, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.damage = damage;
        this.strenght = strenght;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbItem(ItemStack item, String name, List<String> description, List<SbAbility> abilities, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.description = description;
        this.abilities = abilities;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public SbItem(ItemStack item, String name, int damage, int strenght, boolean canBeReforged, SbRarity sbRarity) {
        this.item = item;
        this.name = name;
        this.damage = damage;
        this.strenght = strenght;
        this.canBeReforged = canBeReforged;
        this.sbRarity = sbRarity;
        this.setLore(item);
    }

    public void setLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(sbRarity.getColor() + name);
        if (swingRange > 0) {
            lore.add(Utils.color("&7Swing Range: &c" + swingRange));
        }
        if (damage > 0) {
            lore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+" + damage);
        }
        if (strenght > 0) {
            lore.add(ChatColor.GRAY + "Strenght: " + ChatColor.RED + "+" + strenght);
        }
        if (critChance > 0) {
            lore.add(ChatColor.GRAY + "Critical Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if (critDamage > 0) {
            lore.add(ChatColor.GRAY + "Critical Damage: " + ChatColor.RED + "+" + critDamage + "%");
        }
        if (mana > 0) {
            lore.add(Utils.color("&7Intelligence: &a+" + mana));
        }
        if (ferocity > 0) {
            lore.add(Utils.color("&7Ferocity: &a+" + ferocity));
        }
        if (speed > 0) {
            lore.add(Utils.color("&7Speed: &a+" + speed));
        }
        if (defense > 0) {
            lore.add(Utils.color("&7Defense: &a+" + defense));
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
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "attack_speed", 1024, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(SkyBlock.getInstance(), "item_key");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ChatColor.stripColor(meta.getDisplayName().toLowerCase().replace(" ", "_").replace("'", "")));
        item.setItemMeta(meta);
    }

    public boolean equals(SbItem sbItem) {
        return Utils.getStringFromItem(getItem(), "item_key").equals(Utils.getStringFromItem(sbItem.getItem(), "item_key"));
    }

    public ItemStack getItem(){
        return item;
    }

    public SbItem setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    public String getName(){
        return name;
    }

    public SbItem setName(String name) {
        this.name = name;
        return this;
    }

    public int getDamage() {
        return damage;
    }

    public SbItem setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public void addDamage(int amount) {
        damage+=amount;
        Utils.changeLore(item, "Damage: ", "&7Damage: &c+" + damage);
    }

    public int getStrenght() {
        return strenght;
    }

    public SbItem setStrenght(int strenght) {
        this.strenght = strenght;
        return this;
    }

    public void addStrenght(int amount) {
        strenght+=amount;
        Utils.changeLore(item, "Strenght: ", "&7Damage: &c+" + strenght);
    }

    public int getCritChance() {
        return critChance;
    }

    public SbItem setCritChance(int critChance) {
        this.critChance = critChance;
        return this;
    }

    public int getCritDamage() {
        return critDamage;
    }

    public SbItem setCritDamage(int critDamage) {
        this.critDamage = critDamage;
        return this;
    }

    public int getMana() {
        return mana;
    }

    public SbItem setMana(int mana) {
        this.mana = mana;
        return this;
    }

    public int getSpeed() {
        return speed;
    }

    public SbItem setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public int getDefense() {
        return defense;
    }

    public SbItem setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public List<String> getDescription(){
        return description;
    }

    public SbItem setDescription(List<String> description) {
        this.description = description;
        return this;
    }

    public List<SbAbility> getAbilities() {
        return abilities;
    }

    public SbItem setAbilities(List<SbAbility> abilities) {
        this.abilities = abilities;
        return this;
    }

    public boolean isCanBeReforged() {
        return canBeReforged;
    }

    public SbItem setCanBeReforged(boolean canBeReforged) {
        this.canBeReforged = canBeReforged;
        return this;
    }

    public SbRarity getSbRarity(){
        return sbRarity;
    }

    public SbItem setSbRarity(SbRarity sbRarity) {
        this.sbRarity = sbRarity;
        return this;
    }

    public int getFerocity() {
        return ferocity;
    }

    public void setFerocity(int ferocity) {
        this.ferocity = ferocity;
    }

    public int getSwingRange() {
        return swingRange;
    }

    public void setSwingRange(int swingRange) {
        this.swingRange = swingRange;
    }
}
