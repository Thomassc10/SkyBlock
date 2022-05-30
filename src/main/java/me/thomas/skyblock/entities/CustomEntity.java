package me.thomas.skyblock.entities;

import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class CustomEntity extends EntityCreature {

    protected CustomEntity(EntityTypes<? extends EntityCreature> entitytypes, Location loc) {
        super(entitytypes, ((CraftWorld)loc.getWorld()).getHandle());
    }
}
