package me.thomas.skyblock.player.skills;

public class Skills {

    private final CombatSkill combatSkill;
    private final AlchemySkill alchemySkill;
    private final EnchantingSkill enchantingSkill;
    private final FarmingSkill farmingSkill;
    private final ForagingSkill foragingSkill;
    private final MiningSkill miningSkill;
    private static SkillType skillType;
    public Skills() {
        combatSkill = new CombatSkill();
        alchemySkill = new AlchemySkill();
        enchantingSkill = new EnchantingSkill();
        farmingSkill = new FarmingSkill();
        foragingSkill = new ForagingSkill();
        miningSkill = new MiningSkill();
    }

    public static void setSkillType(SkillType skillType){
        Skills.skillType = skillType;
    }

    public CombatSkill getCombatSkill(){
        return combatSkill;
    }

    public AlchemySkill getAlchemySkill() {
        return alchemySkill;
    }

    public EnchantingSkill getEnchantingSkill() {
        return enchantingSkill;
    }

    public FarmingSkill getFarmingSkill() {
        return farmingSkill;
    }

    public ForagingSkill getForagingSkill() {
        return foragingSkill;
    }

    public MiningSkill getMiningSkill() {
        return miningSkill;
    }
}
