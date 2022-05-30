package me.thomas.skyblock.entities;

import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class SbEntities {

    private static final Map<String, SbEntity> mobs = new HashMap<>();
    private static final Map<String, Entity> mobTypes = new HashMap<>();

    public static void registerEntities() {
        //mobs.put("mob", new SbEntity(null).setMaxHealth(0).setLevel(1).setDamage(0).setCoinsDropped(1).setCombatXP(0).setExpOrbsDropped(0));
        // hub
        mobs.put("zombie", new SbEntity(SBEntityType.ZOMBIE).setMaxHealth(100).setLevel(1).setDamage(20).setCoinsDropped(1).setCombatXP(6).setExpOrbsDropped(1));
        mobs.put("zombie_villager", new SbEntity(SBEntityType.ZOMBIE_VILLAGER).setMaxHealth(120).setLevel(1).setDamage(24).setCoinsDropped(1).setCombatXP(7).setExpOrbsDropped(2));
        mobs.put("crypt_ghoul", new SbEntity(SBEntityType.CRYPT_GHOUL).setMaxHealth(2000).setLevel(30).setDamage(350).setCoinsDropped(13).setCombatXP(32).setExpOrbsDropped(20));
        mobs.put("golden_ghoul", new SbEntity(SBEntityType.GOLDEN_GHOUL).setMaxHealth(45000).setLevel(60).setDamage(800).setCoinsDropped(100).setCombatXP(50).setExpOrbsDropped(30));
        mobs.put("wolf", new SbEntity(SBEntityType.WOLF).setMaxHealth(250).setLevel(15).setDamage(90).setCoinsDropped(1).setCombatXP(10).setExpOrbsDropped(4));
        mobs.put("old_wolf", new SbEntity(SBEntityType.OLD_WOLF).setMaxHealth(15000).setLevel(50).setDamage(800).setCoinsDropped(40).setCombatXP(40).setExpOrbsDropped(30));
        // park
        mobs.put("soul_of_the_alpha", new SbEntity(SBEntityType.SOUL_OF_THE_ALPHA).setMaxHealth(31150).setLevel(55).setDamage(1425).setCoinsDropped(50).setCombatXP(50).setExpOrbsDropped(15));
        mobs.put("pack_spirit", new SbEntity(SBEntityType.PACK_SPIRIT).setMaxHealth(6000).setLevel(30).setDamage(300).setCoinsDropped(11).setCombatXP(15).setExpOrbsDropped(10));
        mobs.put("howling_spirit", new SbEntity(SBEntityType.HOWLING_SPIRIT).setMaxHealth(7000).setLevel(35).setDamage(500).setCoinsDropped(11).setCombatXP(15).setExpOrbsDropped(10));
        // farming islands
        mobs.put("sheep", new SbEntity(SBEntityType.SHEEP).setMaxHealth(120).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(1).setCombatXP(3).setExpOrbsDropped(1));
        mobs.put("cow", new SbEntity(SBEntityType.COW).setMaxHealth(50).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(1).setCombatXP(3).setExpOrbsDropped(1));
        mobs.put("chicken", new SbEntity(SBEntityType.CHICKEN).setMaxHealth(50).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(1).setCombatXP(3).setExpOrbsDropped(1));
        mobs.put("rabbit", new SbEntity(SBEntityType.RABBIT).setMaxHealth(130).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(1).setCombatXP(3).setExpOrbsDropped(1));
        mobs.put("pig", new SbEntity(SBEntityType.PIG).setMaxHealth(50).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(1).setCombatXP(3).setExpOrbsDropped(1));
        mobs.put("mushroom_cow", new SbEntity(SBEntityType.MUSHROOM_COW).setMaxHealth(50).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(1).setCombatXP(3).setExpOrbsDropped(1));
        mobs.put("trapper_animals", new SbEntity(SBEntityType.TRAPPER_ANIMALS).setMaxHealth(10000).setLevel(1).setDamage(Integer.MAX_VALUE).setCoinsDropped(10000).setCombatXP(3).setExpOrbsDropped(1));
        // deep caverns
        mobs.put("sneaky_creeper", new SbEntity(SBEntityType.SNEAKY_CREEPER).setMaxHealth(120).setLevel(3).setDamage(80).setCoinsDropped(3).setCombatXP(8).setExpOrbsDropped(3));
        mobs.put("lapis_zombie", new SbEntity(SBEntityType.LAPIS_ZOMBIE).setMaxHealth(200).setLevel(7).setDamage(50).setCoinsDropped(5).setCombatXP(12).setExpOrbsDropped(10));
        mobs.put("redstone_pigman", new SbEntity(SBEntityType.REDSTONE_PIGMAN).setMaxHealth(250).setLevel(55).setDamage(75).setCoinsDropped(12).setCombatXP(20).setExpOrbsDropped(25));
        //mobs.put("emerald_slime", new SbEntity(SBEntityType.EMERALD_SLIME).setMaxHealth(250).setLevel(15).setDamage(150).setCoinsDropped(12).setCombatXP(20).setExpOrbsDropped(50));
        mobs.put("miner_zombie", new SbEntity(SBEntityType.MINER_ZOMBIE).setMaxHealth(250).setLevel(15).setDamage(200).setCoinsDropped(12).setCombatXP(20).setExpOrbsDropped(30));
        mobs.put("miner_skeleton", new SbEntity(SBEntityType.MINER_SKELETON).setMaxHealth(250).setLevel(15).setDamage(200).setCoinsDropped(12).setCombatXP(20).setExpOrbsDropped(30));
    }

    public static Map<String, SbEntity> getMobs() {
        return mobs;
    }

    public static Map<String, Entity> getMobTypes() {
        return mobTypes;
    }
}
