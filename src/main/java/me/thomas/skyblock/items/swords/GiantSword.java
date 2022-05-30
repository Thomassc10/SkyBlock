package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.Arrays;
import java.util.Collections;

public class GiantSword extends me.thomas.skyblock.items.SbItem implements Listener {

    public GiantSword() {
        super(new ItemStack(Material.IRON_SWORD), "Giant's Sword", 500, 0, null, Collections.singletonList(
                new SbAbility("Giant's Slam", AbilityType.RIGHT_CLICK, Arrays.asList("&7Slam your sword into the ground",
                        "&7dealing &c100,000 &7damage to", "&7nearby enemies."), 100, 30)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        EntityGiantZombie zombie = new EntityGiantZombie(EntityTypes.G, ((CraftWorld)player.getWorld()).getHandle());
        zombie.setSilent(true);
        zombie.setInvisible(true);
        zombie.setInvulnerable(true);
        zombie.setCustomName(new ChatComponentText("Dinnerbone"));
        EntityEquipment equipment = ((LivingEntity) zombie.getBukkitEntity()).getEquipment();
        equipment.setItemInMainHand(new ItemStack(Material.IRON_SWORD));

        Location loc = player.getLocation().clone();
        loc.setPitch(0);
        RayTraceResult traceResult = player.getWorld().rayTraceBlocks(loc, loc.getDirection(), 6);
        Location location = traceResult.getHitPosition().toLocation(player.getWorld());
        zombie.setPosition(location.getX(), location.getY(), location.getZ());

        WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
        world.addEntity(zombie);
        player.getWorld().playSound(zombie.getBukkitEntity().getLocation(), Sound.BLOCK_ANVIL_FALL, 2, 1);
        for (Entity e : zombie.getBukkitEntity().getNearbyEntities(3, 3, 3)) {
            if (e instanceof LivingEntity && !(e instanceof Player)) {
                ((LivingEntity) e).damage(100000);
            }
        }
        Utils.scheduleTask(() -> zombie.getBukkitEntity().remove(), 100);
    }
}
