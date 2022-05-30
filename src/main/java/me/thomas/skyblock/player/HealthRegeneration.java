package me.thomas.skyblock.player;

import me.thomas.skyblock.SkyBlock;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthRegeneration extends BukkitRunnable {

    public HealthRegeneration() {
        this.runTaskTimer(SkyBlock.getInstance(), 1, 40);
    }
    @Override
    public void run() {
        for (SbPlayer sbPlayer : PlayerManager.getPlayerManager().getSBPlayers()) {
            if (sbPlayer.getPlayer() != null) {
                double health = (sbPlayer.getMaxHealth() * 0.01) + 1.15 /* Multiplier (Rejuvenate, Zombie Slayer...*/;
                sbPlayer.regenHealth(health);
            }
        }
    }
}
