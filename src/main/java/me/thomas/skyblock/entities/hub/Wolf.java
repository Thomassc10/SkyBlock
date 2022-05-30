package me.thomas.skyblock.entities.hub;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.EntityWolf;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class Wolf extends EntityWolf {

    public Wolf(Location loc) {
        super(EntityTypes.bc, ((CraftWorld)loc.getWorld()).getHandle());
    }
}
