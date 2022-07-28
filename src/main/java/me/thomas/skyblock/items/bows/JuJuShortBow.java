package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class JuJuShortBow extends SbItem implements Listener {

    private final Map<UUID, Long> cooldown = new HashMap<>();
    public JuJuShortBow() {
        super(new ItemStack(Material.BOW), "Juju Shortbow", 310, 40, 10, 110, 0, 0, 0, Arrays.asList("&5Shortbow: Instantly shoots!",
                "&7Hit &c3 &7mobs on impact.", "&7Can damage enderman."), null, true, SbRarity.EPIC_BOW);
    }

    @EventHandler
    public void onPull(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        event.setCancelled(true);
        if (cooldown.containsKey(player.getUniqueId())){
            if (cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                return;
            }
        }
        Arrow arrow = player.launchProjectile(Arrow.class);
        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 600);
        arrow.setVelocity(player.getLocation().getDirection().multiply(3));
        Utils.setIntInEntity(arrow, "juju_bow", 1);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        if (!player.getInventory().getItemInMainHand().isSimilar(this.getItem())) return;
        if (event.getHitEntity() != null) {
            Entity entity = event.getHitEntity();
            List<Entity> nearEntities = entity.getNearbyEntities(1, 1, 1);
            List<LivingEntity> living = new ArrayList<>();
            for (Entity e : nearEntities) {
                if (e instanceof LivingEntity)
                    living.add((LivingEntity) e);
            }
            for (int i = 0; i < 3; i++) {
                LivingEntity le = living.get(i);
                le.damage(entity.getLastDamageCause().getDamage());
            }
        }
    }

    @EventHandler
    public void onEndermanHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
        if (event.getHitEntity().getType() != EntityType.ENDERMAN) return;
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        if (Utils.getIntFromEntity(event.getHitEntity(), "juju_bow") == 1) {
            event.setCancelled(true);
            SbItem sbItem = Items.getSbItem(player.getInventory().getItemInMainHand());
            ((LivingEntity)event.getHitEntity()).damage(Utils.getMeleeDamage(PlayerManager.getPlayerManager().getSBPlayer(player),
                    sbItem, false));
        }
    }
}
