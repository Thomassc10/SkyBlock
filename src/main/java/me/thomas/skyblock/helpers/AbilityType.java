package me.thomas.skyblock.helpers;

public enum AbilityType {

    LEFT_CLICK("LEFT CLICK"),
    RIGHT_CLICK("RIGHT CLICK"),
    MIDDLE_CLICK("MIDDLE CLICK"),
    FULL_SET_BONUS("FULL SET BONUS"),
    PIECE_BONUS("PIECE BONUS"),
    EXTRA_BONUS("EXTRA BONUS"),
    NONE("");

    private final String text;
    AbilityType(final String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
