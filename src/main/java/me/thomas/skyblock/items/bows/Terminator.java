package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class Terminator extends SbItem implements Listener {

    private final Map<UUID, Long> cooldown = new HashMap<>();
    public Terminator() {
        super(new ItemStack(Material.BOW), "Terminator", 310, 50, 0, 250, 0, 0, 0, 0,
                Arrays.asList("&6Shortbow: Instantly shoots!", "&7Shoots &b3 &7arrow at once.", "&7Can damage enderman.",
                        "", "&cDivides your &9%critChance%Crit Chance &cby 4!"), Collections.singletonList(
                        new SbAbility("Salvation", AbilityType.RIGHT_CLICK, Arrays.asList(
                                "&7Can be cast after landing &63 hits.", "&7Shoots a beam, penetrating up",
                                "&7to &e6 &7foes and dealing &c2x", "&7the damage and arrow would.",
                                "&7The beam always crits.", "&8Soulflow Cost: &31%soulFlow%"), 0, 2)), true, SbRarity.LEGENDARY_BOW);
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
        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 400);
        arrow.setVelocity(player.getLocation().getDirection().multiply(3));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        Player player = (Player) event.getDamager();
        int hits = Utils.getIntFromEntity(player, "terminator");
        if (hits < 3)
            Utils.setIntInEntity(player, "terminator", hits + 1);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        if (Utils.getIntFromEntity(player, "terminator") == 3) {
            Utils.setIntInEntity(player, "terminator", 0);
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection();
            for (int i = 0; i < 30; i+=0.1) {
                dir.multiply(i);
                loc.add(dir);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 1);
                player.getWorld().getNearbyEntities(loc, 0.2, 0.2, .02).forEach(e -> {
                    if (e != null)
                        ((LivingEntity)e).damage(Utils.getMeleeDamage(event.getSbPlayer(), event.getSbItem(), true) * 2);
                });
                loc.subtract(dir);
                dir.normalize();
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
