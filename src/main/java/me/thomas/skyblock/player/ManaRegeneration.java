package me.thomas.skyblock.player;

import me.thomas.skyblock.SkyBlock;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaRegeneration extends BukkitRunnable {

    public ManaRegeneration() {
        this.runTaskTimer(SkyBlock.getInstance(), 1, 20);
    }
    @Override
    public void run() {
        for (SbPlayer sbPlayer : PlayerManager.getPlayerManager().getSBPlayers()) {
            if (sbPlayer.getPlayer() != null) {
                double mana = sbPlayer.getMaxMana() * 0.02;
                sbPlayer.regenMana(mana);
            }
        }
    }
}
