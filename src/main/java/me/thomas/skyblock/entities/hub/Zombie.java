package me.thomas.skyblock.entities.hub;

import me.thomas.skyblock.entities.SbEntities;
import me.thomas.skyblock.entities.SbEntity;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class Zombie extends EntityZombie {

    SbEntity sbEntity = SbEntities.getMobs().get("zombie");

    public Zombie(Location loc) {
        super(EntityTypes.be, ((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setBaby(false);
        this.setCanPickupLoot(false);

        this.setHealth(sbEntity.getMaxHealth());
        this.setCustomName(new ChatComponentText("{\"text\":\"" + sbEntity.getName() + "\"}"));
        this.setCustomNameVisible(true);

        SbEntities.getMobTypes().put("zombie", this);
    }

    @Override
    public void initPathfinder() {
        this.bP.a(1, new PathfinderGoalFloat(this));
        this.bP.a(2, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 4.0F));
        this.bP.a(3, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
    }

    /*@Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }*/



    public void setHealth(int i) {
        this.setHealth(i);
    }
}
