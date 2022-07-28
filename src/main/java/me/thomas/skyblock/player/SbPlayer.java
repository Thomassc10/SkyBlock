package me.thomas.skyblock.player;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.player.skills.Skills;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SbPlayer {

    private final UUID uuid;
    private final Skills skills;
    private final Purse purse;
    private int taskID;
    private int strenght;
    private int criticalDamage;
    private int criticalChance;
    private int maxHealth;
    private int defense;
    private int mana;
    private int maxMana;
    private float speed;
    private int ferocity;
    public SbPlayer(UUID uuid) {
        this.uuid = uuid;
        skills = new Skills();
        purse = new Purse();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Skills getSkills(){
        return skills;
    }

    public Purse getPurse() {
        return purse;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getStrenght(){
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public void addStrenght(int amount) {
        strenght+=amount;
    }

    public void removeStrenght(int amount) {
        strenght-=amount;
    }

    public int getCriticalDamage(){
        return criticalDamage;
    }

    public void setCriticalDamage(int criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    public void addCriticalDamage(int amount) {
        criticalDamage+=amount;
    }

    public void removeCriticalDamage(int amount) {
        criticalDamage-=amount;
    }

    public int getCriticalChance() {
        if (Utils.getStringFromItem(getPlayer().getInventory().getItemInMainHand(), "item_key").equals("terminator"))
            return criticalChance / 4;
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }

    public void addCriticalChance(int amount) {
        criticalChance+=amount;
    }

    public void removeCriticalChance(int amount) {
        criticalChance-=amount;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void addMana(double amount){
        mana+=amount;
    }

    public void removeMana(int amount) {
        mana-=amount;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public void setMaxMana(int mana) {
        this.maxMana = mana;
    }

    public void addMaxMana(int amount) {
        maxMana+=amount;
    }

    public void removeMaxMana(int amount) {
        maxMana-=amount;
    }

    public void regenMana(double amount) {
        if (mana >= maxMana) return;
        if ((mana + amount) <= maxHealth)
            addMana(amount);
        else setMana(maxMana);
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
    }

    public void addMaxHealth(int amount) {
        getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth + amount);
    }

    public void removeMaxHealth(int amount) {
        getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth - amount);
    }

    public void regenHealth(double amount) {
        if (getPlayer().getHealth() >= maxHealth) return;
        if ((getPlayer().getHealth() + amount) <= getMaxHealth())
            getPlayer().setHealth(getPlayer().getHealth() + amount);
        else getPlayer().setHealth(getMaxHealth());
    }

    public int getDefense(){
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void addDefense(int amount) {
        defense+=amount;
    }

    public void removeDefense(int amount) {
        defense-=amount;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void addSpeed(float amount) {
        speed+=amount;
        amount = (float) ((amount/100) * 0.2);
        getPlayer().setWalkSpeed(getPlayer().getWalkSpeed() + amount);
    }

    public void removeSpeed(float amount) {
        speed-=amount;
        amount = (float) ((amount/100) * 0.2);
        getPlayer().setWalkSpeed(getPlayer().getWalkSpeed() - amount);
    }

    public int getFerocity() {
        return ferocity;
    }

    public void setFerocity(int ferocity) {
        this.ferocity = ferocity;
    }

    public void addFerocity(int amount) {
        ferocity+=amount;
    }

    public void removeFerocity(int amount) {
        ferocity-=amount;
    }
}
