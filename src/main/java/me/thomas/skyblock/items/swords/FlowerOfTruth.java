package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import net.minecraft.core.Vector3f;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlowerOfTruth extends me.thomas.skyblock.items.SbItem implements Listener {

    public FlowerOfTruth() {
        super(new ItemStack(Material.POPPY), "Flower Of Truth", 160, 300, null, Collections.singletonList(
                new SbAbility("Heat-Seeking Rose", AbilityType.RIGHT_CLICK, Arrays.asList("&7Shoots a rose that ricochets",
                        "&7between enemies, damaging up to", "&a3 &7of your foes! Damage",
                        "&7multiplies as more enemies are", "&7hit.", "&7The mana cost of this item is",
                        "&a10.0% &7of your maximum mana."), 10, 1)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
        armorStand.setInvulnerable(true);
        armorStand.setInvisible(true);
        armorStand.setMarker(true);
        armorStand.setPosition(loc.getX(), loc.getY() + 1, loc.getZ());
        armorStand.setArms(true);
        EntityEquipment equipment = ((LivingEntity) armorStand.getBukkitEntity()).getEquipment();
        equipment.setItemInMainHand(new ItemStack(Material.POPPY));
        armorStand.setBodyPose(new Vector3f(0, 0, 0));

        WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
        world.addEntity(armorStand);

        new BukkitRunnable() {
            boolean first;
            @Override
            public void run() {
                double damage = Utils.getMeleeDamage(event.getSbPlayer(), event.getSbItem());
                List<LivingEntity> near = null;
                if (!first) {
                    List<LivingEntity> entities = Utils.getNearestEntities(armorStand.getBukkitEntity(),0.5);
                    if (entities.isEmpty()) {
                        armorStand.getBukkitEntity().setVelocity(loc.getDirection().multiply(1));
                        return;
                    }
                    LivingEntity entity = entities.get(0);

                    entity.damage(damage);
                    first = true;
                    near = Utils.getNearestEntities(armorStand.getBukkitEntity(), 3);
                    if (near.isEmpty()) return;
                }
                if (first) {
                    armorStand.getBukkitEntity().setVelocity(near.get(0).getLocation().getDirection().multiply(3));
                    near.get(0).damage(damage);
                    if (near.size() == 2) {
                        armorStand.getBukkitEntity().setVelocity(near.get(1).getLocation().getDirection().multiply(3));
                        near.get(1).damage(damage);
                    }
                    cancel();
                    armorStand.getBukkitEntity().remove();
                }

                if (armorStand.getBukkitEntity().getLocation().distance(loc) >= 50) {
                    armorStand.getBukkitEntity().remove();
                    cancel();
                }
            }
        }.runTaskTimer(SkyBlock.getInstance(), 1, 1);
    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(event, getItem())) return;
        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        SbItem sbItem = Items.getSbItem(item);
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        int manaCost = (int) Utils.getPercent(sbPlayer.getMaxMana(), 10);
        Utils.changeLore(item, "Mana Cost: ", "&8Mana Cost: &b" + manaCost);
        sbItem.getAbilities().get(0).setManaCost(manaCost);
    }
}
