package me.thomas.skyblock.helpers;

public enum StatType {

    SWING_RANGE("Swing Range: "),
    DAMAGE("Damage: "),
    STRENGHT("Strenght: "),
    CRITICAL_CHANCE("Critical Chance: "),
    CRITICAL_DAMAGE("Critical Damage: "),
    INTELLIGENCE("Intelligence: "),
    FEROCITY("Ferocity: "),
    SPEED("Speed: "),
    SEA_CREATURE_CHANCE("Sea Creature Chance: "),
    DEFENSE("Defense: "),
    HEALTH("Health: ");

    private final String text;
    StatType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
