package me.thomas.skyblock.helpers;

import org.bukkit.ChatColor;

public enum SbRarity {

    COMMON_SWORD(ChatColor.WHITE),
    UNCOMMON_SWORD(ChatColor.GREEN),
    RARE_SWORD(ChatColor.BLUE),
    EPIC_SWORD(ChatColor.DARK_PURPLE),
    LEGENDARY_SWORD(ChatColor.GOLD),
    MYTHIC_SWORD(ChatColor.LIGHT_PURPLE),
    SPECIAL_SWORD(ChatColor.DARK_RED),

    LEGENDARY_LONGSWORD(ChatColor.GOLD),

    COMMON_PICKAXE(ChatColor.WHITE),
    UNCOMMON_PICKAXE(ChatColor.GREEN),
    RARE_PICKAXE(ChatColor.BLUE),
    EPIC_PICKAXE(ChatColor.DARK_PURPLE),
    LEGENDARY_PICKAXE(ChatColor.GOLD),
    MYTHIC_PICKAXE(ChatColor.LIGHT_PURPLE),
    SPECIAL_PICKAXE(ChatColor.DARK_RED),

    COMMON_AXE(ChatColor.WHITE),
    UNCOMMON_AXE(ChatColor.GREEN),
    RARE_AXE(ChatColor.BLUE),
    EPIC_AXE(ChatColor.DARK_PURPLE),
    LEGENDARY_AXE(ChatColor.GOLD),
    MYTHIC_AXE(ChatColor.LIGHT_PURPLE),
    SPECIAL_AXE(ChatColor.DARK_RED),

    COMMON_SHOVEL(ChatColor.WHITE),
    UNCOMMON_SHOVEL(ChatColor.GREEN),
    RARE_SHOVEL(ChatColor.BLUE),
    EPIC_SHOVEL(ChatColor.DARK_PURPLE),
    LEGENDARY_SHOVEL(ChatColor.GOLD),
    MYTHIC_SHOVEL(ChatColor.LIGHT_PURPLE),
    SPECIAL_SHOVEL(ChatColor.DARK_RED),

    COMMON_HOE(ChatColor.WHITE),
    UNCOMMON_HOE(ChatColor.GREEN),
    RARE_HOE(ChatColor.BLUE),
    EPIC_HOE(ChatColor.DARK_PURPLE),
    LEGENDARY_HOE(ChatColor.GOLD),
    MYTHIC_HOE(ChatColor.LIGHT_PURPLE),
    SPECIAL_HOE(ChatColor.DARK_RED),

    COMMON_BOW(ChatColor.WHITE),
    UNCOMMON_BOW(ChatColor.GREEN),
    RARE_BOW(ChatColor.BLUE),
    EPIC_BOW(ChatColor.DARK_PURPLE),
    LEGENDARY_BOW(ChatColor.GOLD),
    MYTHIC_BOW(ChatColor.LIGHT_PURPLE),
    SPECIAL_BOW(ChatColor.DARK_RED),

    COMMON_HELMET(ChatColor.WHITE),
    UNCOMMON_HELMET(ChatColor.GREEN),
    RARE_HELMET(ChatColor.BLUE),
    EPIC_HELMET(ChatColor.DARK_PURPLE),
    LEGENDARY_HELMET(ChatColor.GOLD),
    MYTHIC_HELMET(ChatColor.LIGHT_PURPLE),
    SPECIAL_HELMET(ChatColor.DARK_RED),

    COMMON_CHESTPLATE(ChatColor.WHITE),
    UNCOMMON_CHESTPLATE(ChatColor.GREEN),
    RARE_CHESTPLATE(ChatColor.BLUE),
    EPIC_CHESTPLATE(ChatColor.DARK_PURPLE),
    LEGENDARY_CHESTPLATE(ChatColor.GOLD),
    MYTHIC_CHESTPLATE(ChatColor.LIGHT_PURPLE),
    SPECIAL_CHESTPLATE(ChatColor.DARK_RED),

    COMMON_LEGGINGS(ChatColor.WHITE),
    UNCOMMON_LEGGINGS(ChatColor.GREEN),
    RARE_LEGGINGS(ChatColor.BLUE),
    EPIC_LEGGINGS(ChatColor.DARK_PURPLE),
    LEGENDARY_LEGGINGS(ChatColor.GOLD),
    MYTHIC_LEGGINGS(ChatColor.LIGHT_PURPLE),
    SPECIAL_LEGGINGS(ChatColor.DARK_RED),

    COMMON_BOOTS(ChatColor.WHITE),
    UNCOMMON_BOOTS(ChatColor.GREEN),
    RARE_BOOTS(ChatColor.BLUE),
    EPIC_BOOTS(ChatColor.DARK_PURPLE),
    LEGENDARY_BOOTS(ChatColor.GOLD),
    MYTHIC_BOOTS(ChatColor.LIGHT_PURPLE),
    SPECIAL_BOOTS(ChatColor.DARK_RED),

    COMMON_POWER_ORB(ChatColor.WHITE),
    UNCOMMON_POWER_ORB(ChatColor.GREEN),
    RARE_POWER_ORB(ChatColor.BLUE),
    EPIC_POWER_ORB(ChatColor.DARK_PURPLE),
    LEGENDARY_POWER_ORB(ChatColor.GOLD),
    MYTHIC_POWER_ORB(ChatColor.LIGHT_PURPLE),
    SPECIAL_POWER_ORB(ChatColor.DARK_RED);

    private ChatColor color;
    SbRarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor(){
        return color;
    }
}