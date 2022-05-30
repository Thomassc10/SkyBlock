package me.thomas.skyblock.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawn implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event){
        //if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) return;
        //if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) return;

        /*EntityManager entityManager = EntityManager.getEntityManager();
        SBEntity sbEntity = entityManager.getSBEntity(event.getEntity());
        LivingEntity livingEntity = event.getEntity();

        ArmorStand armorStand = livingEntity.getWorld().spawn(livingEntity.getEyeLocation(), ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName("[lvl" + sbEntity.getLevel() + "] " + sbEntity.getHealth() + "/" + sbEntity.getHealth());
        armorStand.setCustomNameVisible(true);

        livingEntity.addPassenger(armorStand);*/
    }
}
