package me.thomas.skyblock.events;

import me.thomas.skyblock.entities.SBEntityType;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageReceive implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getDamager() instanceof Player) return;
        Player player = (Player) event.getEntity();
        PlayerManager playerManager = PlayerManager.getPlayerManager();
        SbPlayer sbPlayer = playerManager.getSBPlayer(player);
        //SBEntity sbEntity = EntityManager.getEntityManager().getSBEntity(event.getDamager());
        int defense = sbPlayer.getDefense();
        int reduced = defense / (defense + 100);
        double damage = event.getDamage();
        double difference = damage * reduced;
        double finalDamage = damage - difference;
        event.setDamage(finalDamage);
    }
}
