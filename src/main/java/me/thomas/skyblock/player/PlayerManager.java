package me.thomas.skyblock.player;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {

    private final List<SbPlayer> sbPlayers;
    private static PlayerManager playerManager;
    public PlayerManager(){
        playerManager = this;
        sbPlayers = Collections.synchronizedList(new ArrayList<>());
    }

    public static PlayerManager getPlayerManager(){
        return playerManager;
    }

    public List<SbPlayer> getSBPlayers() {
        return sbPlayers;
    }

    public void addSBPlayer(SbPlayer sbPlayer){
        if (!sbPlayers.contains(sbPlayer))
        sbPlayers.add(sbPlayer);
    }

    public SbPlayer getSBPlayer(Player player){
        for (SbPlayer sbPlayer : sbPlayers){
            if (player.getUniqueId().equals(sbPlayer.getUuid())) {
                return sbPlayer;
            }
        }
        return null;
    }
}
