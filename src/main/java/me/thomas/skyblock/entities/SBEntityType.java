package me.thomas.skyblock.entities;

import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityTypes;

public enum SBEntityType {

    // Hub
    ZOMBIE(EntityTypes.be),
    ZOMBIE_VILLAGER(EntityTypes.be),
    CRYPT_GHOUL(EntityTypes.be),
    GOLDEN_GHOUL(EntityTypes.be),
    WOLF(EntityTypes.bc),
    OLD_WOLF(EntityTypes.bc),

    // Park
    SOUL_OF_THE_ALPHA(EntityTypes.bc),
    PACK_SPIRIT(EntityTypes.bc),
    HOWLING_SPIRIT(EntityTypes.bc),

    // The Farming Islands
    SHEEP(EntityTypes.ax),
    COW(EntityTypes.n),
    CHICKEN(EntityTypes.l),
    RABBIT(EntityTypes.au),
    PIG(EntityTypes.an),
    MUSHROOM_COW(EntityTypes.ah),
    TRAPPER_ANIMALS(EntityTypes.au),

    // Deep Caverns
    SNEAKY_CREEPER(EntityTypes.o),
    LAPIS_ZOMBIE(EntityTypes.be),
    REDSTONE_PIGMAN(EntityTypes.ao),
    //EMERALD_SLIME(EntityTypes.aD),
    MINER_ZOMBIE(EntityTypes.be),
    MINER_SKELETON(EntityTypes.aB);

    SBEntityType(EntityTypes<? extends EntityCreature> entityTypes) {
    }
}
