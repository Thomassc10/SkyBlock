package me.thomas.skyblock.entities.hub;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class CryptGhoul extends EntityZombie {

    public CryptGhoul(Location loc) {
        super(EntityTypes.be, ((CraftWorld)loc.getWorld()).getHandle());
    }
}
