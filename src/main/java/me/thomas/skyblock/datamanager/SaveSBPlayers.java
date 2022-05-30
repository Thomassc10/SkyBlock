package me.thomas.skyblock.datamanager;

import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.configuration.file.FileConfiguration;

public class SaveSBPlayers {

    public static void savePlayers(){
        PlayerManager playerManager = PlayerManager.getPlayerManager();
        DataManager dataManager = DataManager.getDataManager();
        FileConfiguration config = dataManager.getSBPlayersConfig().getConfig();
        for (SbPlayer sbPlayer : playerManager.getSBPlayers()) {
            config.set("players." + sbPlayer.getUuid(), sbPlayer);
        }
        dataManager.getSBPlayersConfig().saveConfig();
    }

    public static void loadPlayers(){
        PlayerManager playerManager = PlayerManager.getPlayerManager();
        DataManager dataManager = DataManager.getDataManager();
        FileConfiguration config = dataManager.getSBPlayersConfig().getConfig();

    }
}
