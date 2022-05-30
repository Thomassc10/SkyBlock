package me.thomas.skyblock.helpers;

public class Placeholder {

    private final String[] placeholders = {"health", "defense", "strenght", "speed", "critChance", "critDamage", "intelligence",
            "miningSpeed", "attackSpeed", "seaCreatureChance", "magicFind", "petLuck", "trueDefense",
            "ferocity", "abilityDamage", "fortune", "pristine", "location" , "day", "night", "complete", "incomplete",
            "soulFlow"};

    public String replaceLines(String line) {
        for (String placeholder : placeholders) {
            if (line.contains("%" + placeholder + "%")) {
                line = line.replaceAll("%" + placeholder + "%", this.getReplacement(placeholder));
            }
        }
        return line;
    }

    public String getReplacement(String placeholder) {
        switch(placeholder) {
            case "health":
                return "❤";
            case "defense":
                return "❈";
            case "strenght":
                return "❁";
            case "speed":
                return "✦";
            case "critChance":
                return "☣";
            case "critDamage":
                return "☠";
            case "intelligence":
                return "✎";
            case "miningSpeed":
                return "⸕";
            case "attackSpeed":
                return "⚔";
            case "seaCreatureChance":
                return "α";
            case "magicFind":
                return "✯";
            case "petLuck":
                return "♣";
            case "trueDefense":
                return "❂";
            case "ferocity":
                return "⫽";
            case "abilityDamage":
                return "๑";
            case "fortune":
                return "☘";
            case "pristine":
                return "✧";
            case "location":
                return "⏣";
            case "day":
                return "☀";
            case "night":
                return "☽";
            case "complete":
                return "✔";
            case "incomplete":
                return "✖";
            case "soulFlow":
                return "⸎";
            default:
                return "";
        }
    }
}
