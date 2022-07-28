package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;

public class LividDagger extends me.thomas.skyblock.items.SbItem implements Listener {

    public LividDagger() {
        super(new ItemStack(Material.IRON_SWORD), "Livid Dagger", 210, 60, 100, 50, 0, 0, 0, 
                Arrays.asList("&7Your Critical Hits deal &9100%", "&7more damage if you are behind", "&7your target."), Collections.singletonList(
                        new SbAbility("Throw", AbilityType.RIGHT_CLICK, Arrays.asList("&7Throw your dagger at your",
                                "&7enemies!"), 150, 5)), true, SbRarity.LEGENDARY_SWORD);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        EntityArmorStand armorStand = new EntityArmorStand(EntityTypes.c, ((CraftWorld)player.getWorld()).getHandle());
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!Utils.isRightItem(event, getItem())) return;
        Player player = (Player) event.getDamager();
        Vector pDir = player.getLocation().getDirection();
        Vector eDir = event.getEntity().getLocation().getDirection();
        double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
        double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
        double angle = Math.atan2(xv, zv);
        double angleInDegrees = (angle * 180) / Math.PI;

        if (angleInDegrees <= 60 && angleInDegrees >= -32) {
            event.setDamage(event.getDamage() * 2);
        }
    }
}
