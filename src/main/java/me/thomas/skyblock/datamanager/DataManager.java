package me.thomas.skyblock.datamanager;

import me.thomas.skyblock.SkyBlock;

public class DataManager {

    public SkyBlock plugin;
    private static DataManager dataManager;

    private ConfigFile sbPlayersConfig;
    public DataManager(SkyBlock plugin){
        dataManager = this;
        this.plugin = plugin;

        sbPlayersConfig = new ConfigFile(plugin, "sbplayers.yml");

        sbPlayersConfig.saveDefaultConfig();
    }

    public static DataManager getDataManager(){
        return dataManager;
    }

    public ConfigFile getSBPlayersConfig() {
        return sbPlayersConfig;
    }
}
