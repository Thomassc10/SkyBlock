package me.thomas.skyblock.items.swords;

import  me.thomas.skyblock.SkyBlock;
import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class TribalSpeer extends SbItem implements Listener {

    public TribalSpeer() {
        super(new ItemStack(Material.STICK), "Tribal Speer", 0, 100, null, Collections.singletonList(new SbAbility("Thwack", AbilityType.RIGHT_CLICK,
                Arrays.asList("&7Throw the spear like an arrow", "&7which will return to you."))), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVelocity(player.getLocation().getDirection().multiply(3));
        Utils.setStringInEntity(arrow, "player_id", player.getUniqueId().toString());
        Utils.setItemInEntity(arrow, "player_item", player.getInventory().getItemInMainHand());
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ARROW) {
            Entity entity = event.getEntity();
            ItemStack item = Utils.getItemFromEntity(entity, "player_item");
            if (entity.getPersistentDataContainer().has(new NamespacedKey(SkyBlock.getInstance(), "player_id"), PersistentDataType.STRING)) {
                Player player = Bukkit.getPlayer(UUID.fromString(Utils.getStringFromEntity(entity, "player_id")));
                if (event.getHitEntity() != null) {
                    Arrow arrow = ((LivingEntity) event.getHitEntity()).launchProjectile(Arrow.class);
                    arrow.setVelocity(player.getLocation().toVector().multiply(5));
                }
                player.getInventory().addItem(item);
            }
        }
    }
}
