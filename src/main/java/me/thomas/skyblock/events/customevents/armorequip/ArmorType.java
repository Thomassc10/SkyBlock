package me.thomas.skyblock.events.customevents.armorequip;

import org.bukkit.inventory.ItemStack;

public enum ArmorType {
    HELMET(5), CHESTPLATE(6), LEGGINGS(7), BOOTS(8);

    private final int slot;

    ArmorType(int slot) {
        this.slot = slot;
    }

    /**
     * Attempts to match the ArmorType for the specified ItemStack.
     *
     * @param itemStack The ItemStack to parse the type of.
     * @return The parsed ArmorType, or null if not found.
     */
    public static ArmorType matchType(ItemStack itemStack) {
        if(EquipArmor.isAirOrNull(itemStack)) return null;
        String type = itemStack.getItemMeta().getDisplayName();
        if(type.contains("Helmet"))return HELMET;
        else if (type.contains("Chestplate")) return CHESTPLATE;
        else if (type.contains("Leggings")) return LEGGINGS;
        else if (type.contains("Boots")) return BOOTS;
        return null;
    }

    public int getSlot() {
        return slot;
    }
}
