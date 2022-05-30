package me.thomas.skyblock.skills;

import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkillLevelUpEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private SbPlayer sbPlayer;
    private SkillType skill;
    private int level;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public SbPlayer getSBPlayer(){
        return sbPlayer;
    }

    public SkillType getSkill(){
        return skill;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}
