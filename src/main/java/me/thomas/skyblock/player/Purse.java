package me.thomas.skyblock.player;

import me.thomas.skyblock.helpers.Utils;
import org.bukkit.entity.Player;

public class Purse {

    private int coins;
    public Purse() {

    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(Player player, int coins) {
        this.coins = coins;
        if (player.getScoreboard().getTeam("purse") != null)
        Utils.updateScoreboardSuffix(player, "purse", coins + "");
    }

    public void addCoins(Player player, int amount) {
        this.coins+=amount;
        Utils.updateScoreboardSuffix(player, "purse", coins + "");
    }

    public void removeCoins(Player player, int amount) {
        coins-=amount;
        Utils.updateScoreboardSuffix(player, "purse", coins + "");
    }
}
