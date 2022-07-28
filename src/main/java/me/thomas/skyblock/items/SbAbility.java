package me.thomas.skyblock.items;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class SbAbility {

    private String name;
    private AbilityType abilityType;
    private List<String> description;
    private int manaCost;
    private int cooldown;
    private boolean inCooldown;
    public SbAbility(String name, AbilityType abilityType, List<String> description, int manaCost, int cooldown) {
        this.name = name;
        this.abilityType = abilityType;
        this.description = description;
        this.cooldown = cooldown;
        this.manaCost = manaCost;
    }

    public SbAbility(String name, AbilityType abilityType, List<String> description, int manaCost) {
        this.name = name;
        this.abilityType = abilityType;
        this.description = description;
        this.manaCost = manaCost;
    }

    public SbAbility(String name, AbilityType abilityType, List<String> description, double armorSetAmount) {
        this.name = name;
        this.abilityType = abilityType;
        this.description = description;
    }

    public SbAbility(String name, AbilityType abilityType, List<String> description) {
        this.name = name;
        this.abilityType = abilityType;
        this.description = description;
    }

    public List<String> toLore() {
        List<String> lore = new ArrayList<>();
        if (abilityType == AbilityType.FULL_SET_BONUS) {
            lore.add(ChatColor.GOLD + "Full Set Bonus: " + this.name);
            for (String d : description)
                lore.add(Utils.color(Utils.icon(d)));
            if (this.cooldown > 0) {
                lore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + this.cooldown + "s.");
            }
        } else if (abilityType == AbilityType.PIECE_BONUS) {
            lore.add(ChatColor.GOLD + "Piece Bonus: " + this.name);
            for (String d : description)
                lore.add(Utils.color(Utils.icon(d)));
        } else if (abilityType == AbilityType.EXTRA_BONUS) {
            lore.add(ChatColor.GOLD + "Extra Bonus: " + ChatColor.GREEN + name);
            for (String d : description)
                lore.add(Utils.color(Utils.icon(d)));
        } else {
            lore.add(ChatColor.GOLD + "Ability: " + this.name + " " + ChatColor.YELLOW + ChatColor.BOLD + this.abilityType.getText());
            for (String d : description)
                lore.add(Utils.color(Utils.icon(d)));
            if (this.manaCost > 0) {
                lore.add(ChatColor.DARK_GRAY + "Mana cost: " + ChatColor.DARK_AQUA + manaCost);
            }
            if (this.cooldown > 0) {
                lore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + this.cooldown + "s.");
            }
        }
        return lore;
    }

    public String getName() {
        return name;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public List<String> getDescription() {
        return description;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public boolean isInCooldown() {
        return inCooldown;
    }
}
