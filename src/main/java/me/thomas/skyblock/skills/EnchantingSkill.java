package me.thomas.skyblock.skills;

public class EnchantingSkill {

    private int level;
    private double currentExp;
    private double expNeeded;
    public EnchantingSkill(){
        Skills.setSkillType(SkillType.ENCHANTING);
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
