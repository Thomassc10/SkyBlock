package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class ReaperScythe extends me.thomas.skyblock.items.SbItem implements Listener {

    public ReaperScythe() {
        super(new ItemStack(Material.DIAMOND_HOE), "Reaper Scythe", 333, 0, 0, 0, 0, 10, 0, null, Collections.singletonList(
                new SbAbility("Raise Souls", AbilityType.RIGHT_CLICK, Arrays.asList("&7Monsters you kill using this",
                        "&7item will drop their soul. You", "&7can click on their souls on the",
                        "&7ground using this item to absorb", "&7them and then spawn them to", "&7fight by your side.", "",
                        "&7Mana cost is based on the power", "&7of the monsters that you summon.",
                        "&7Shift right-click to view and", "&7remove souls from this item. If",
                        "&7your summoned monster dies the", "&7soul will be removed.", "&7Max Souls: &d3"), 0, 1)), true, SbRarity.LEGENDARY_SWORD);
        Utils.setArrayInItem(getItem(), "souls", new long[3]);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        long[] array = Utils.getArrayInItem(player.getInventory().getItemInMainHand(), "souls");
        if (array.length == 0) return;
        if (player.isSneaking()) {
            Inventory inv = Bukkit.createInventory(null, 36, "Souls");
            for (int i = 0; i < array.length; i++) {
                Entity entity = Bukkit.getEntity(UUID.fromString(String.valueOf(array[i])));
                ItemStack item = new ItemStack(Material.matchMaterial(entity.getType().name() + "_SPAWN_EGG"));
                inv.setItem(i, item);
                player.openInventory(inv);
            }
        } else {
            for (Long l : array) {
                Entity entity = Bukkit.getEntity(UUID.fromString(String.valueOf(l)));
                EntityCreature creature = (EntityCreature) entity;
                @SuppressWarnings("unchecked")
                MyEntity myEntity = new MyEntity((EntityTypes<? extends EntityCreature>) creature.getEntityType(), player.getLocation());
                WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
                world.addEntity(myEntity);
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) return;
        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (!Utils.isRightItem(player, getItem())) return;
        LivingEntity lv = event.getEntity();

        ArmorStand armorStand = player.getWorld().spawn(lv.getLocation().clone().subtract(0, 1.5, 0), ArmorStand.class);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.getEquipment().setHelmet(Utils.getValueHead(""));
        Utils.setStringInEntity(armorStand, "id", lv.getUniqueId().toString());
        Utils.scheduleTask(armorStand::remove, 1200);
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (entity.getPersistentDataContainer().has(new NamespacedKey(SkyBlock.getInstance(), "id"), PersistentDataType.STRING)) {
            String uuid = Utils.getStringFromEntity(entity, "id");
            long[] array = Utils.getArrayInItem(player.getInventory().getItemInMainHand(), "souls");
            if (array.length == 3) {
                player.sendMessage(ChatColor.RED + "You cannot acquire any more souls!");
                return;
            }
            long[] newArray = new long[3];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            newArray[array.length - 1] = Long.parseLong(uuid);
            Utils.setArrayInItem(player.getInventory().getItemInMainHand(), "souls", newArray);
            entity.remove();
        }
    }

    private static class MyEntity extends EntityCreature {
        protected MyEntity(EntityTypes<? extends EntityCreature> entityTypes, Location loc) {
            super(entityTypes, ((CraftWorld)loc.getWorld()).getHandle());
        }

        @Override
        protected void initPathfinder() {
            this.bP.a(0, new PathfinderGoalFloat(this));
            this.bP.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, false));
            // custom pathfinder
        }
    }
}
