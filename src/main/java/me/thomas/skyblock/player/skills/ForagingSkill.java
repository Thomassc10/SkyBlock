package me.thomas.skyblock.player.skills;

public class ForagingSkill {

    private int level;
    private double currentExp;
    private double expNeeded;
    public ForagingSkill(){
        Skills.setSkillType(SkillType.FORAGING);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void LevelUp(){
        level++;
    }

    public double getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(double currentExp) {
        this.currentExp = currentExp;
    }

    public double getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(double expNeeded) {
        this.expNeeded = expNeeded;
    }
}
