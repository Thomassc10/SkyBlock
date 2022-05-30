package me.thomas.skyblock.entities.hub;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombieVillager;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class ZombieVillager extends EntityZombieVillager {

    public ZombieVillager(Location loc) {
        super(EntityTypes.bg, ((CraftWorld) loc.getWorld()).getHandle());
    }
}
