package me.thomas.skyblock.skills;

import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkillExpGainEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private SbPlayer sbPlayer;
    private int level;
    private double currentExp;
    private double expNeeded;
    public SkillExpGainEvent(SbPlayer sbPlayer , double currentExp, double expNeeded){
        this.sbPlayer = sbPlayer;
        this.currentExp = currentExp;
        this.expNeeded = expNeeded;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public SbPlayer getSBPlayer() {
        return sbPlayer;
    }

    public double getExpNeeded() {
        return expNeeded;
    }

    public double getCurrentExp() {
        return currentExp;
    }

    public int getLevel() {
        return level;
    }

    public void LevelUp(){
        level++;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

}
