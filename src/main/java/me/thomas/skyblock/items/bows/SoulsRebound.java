package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SoulsRebound extends me.thomas.skyblock.items.SbItem implements Listener {

    public SoulsRebound() {
        super(new ItemStack(Material.BOW), "Souls Rebound", 450, 0, Arrays.asList(
                "&7Your arrows mark enemies you hit", "&7for &b5 &7seconds. Marked",
                "&7Enemies don't take damage from", "you, once the mark expires the",
                "&7target will receive a burst of", "&7damage equal to all the damage",
                "&7dealt during the mark increased", "&7by up to &c20%&7."), null, true, SbRarity.EPIC_BOW);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        if (!Utils.isRightItem(player, getItem())) return;
        if (event.getHitEntity() == null || !(event.getHitEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getHitEntity();
        if (!Utils.getStringFromEntity(entity, "marked").equals("true")) {
            entity.setAI(false);
            Utils.setStringInEntity(entity, "marked", "true");
            Utils.setIntInEntity(entity, "damage", 0);

            Utils.scheduleTask(() -> {
                entity.setAI(true);
                Utils.setStringInEntity(entity, "marked", "false");
                entity.damage(Utils.getIntFromEntity(entity, "damage"));
            }, 100);
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) return;
        Entity entity = event.getEntity();
        if (!Utils.getStringFromEntity(entity, "marked").equals("true")) return;
        int damage = Utils.getIntFromEntity(entity, "damage");
        Utils.setIntInEntity(entity, "damage", (int) (damage + event.getDamage()));
    }
}
