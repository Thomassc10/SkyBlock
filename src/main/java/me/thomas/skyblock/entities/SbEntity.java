package me.thomas.skyblock.entities;

import org.bukkit.ChatColor;

public class SbEntity {

    private final SBEntityType sbEntityType;
    private final String name;
    private int maxHealth;
    private int level;
    private double damage;
    private double coinsDropped;
    private double combatXP;
    private int expOrbsDropped;
    public SbEntity(SBEntityType sbEntityType) {
        this.sbEntityType = sbEntityType;

        String type = sbEntityType.name().replace("_", " ").toLowerCase();
        type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        name = ChatColor.translateAlternateColorCodes('&', "[lv-" + level + "] " + type + " " + maxHealth + "/" + maxHealth);
    }

    public SBEntityType getSbEntityType() {
        return sbEntityType;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public SbEntity setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public SbEntity setLevel(int level) {
        this.level = level;
        return this;
    }

    public double getDamage() {
        return damage;
    }

    public SbEntity setDamage(double damage) {
        this.damage = damage;
        return this;
    }

    public double getCoinsDropped() {
        return coinsDropped;
    }

    public SbEntity setCoinsDropped(double coinsDropped) {
        this.coinsDropped = coinsDropped;
        return this;
    }

    public double getCombatXP() {
        return combatXP;
    }

    public SbEntity setCombatXP(double combatXP) {
        this.combatXP = combatXP;
        return this;
    }

    public int getExpOrbsDropped() {
        return expOrbsDropped;
    }

    public SbEntity setExpOrbsDropped(int expOrbsDropped) {
        this.expOrbsDropped = expOrbsDropped;
        return this;
    }
}
