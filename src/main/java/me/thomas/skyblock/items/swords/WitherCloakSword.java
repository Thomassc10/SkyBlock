package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.events.customevents.abilityuse.AbilityUseEvent;
import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class WitherCloakSword extends me.thomas.skyblock.items.SbItem implements Listener {

    int taskID;
    Map<String, List<UUID>> map = new HashMap<>();

    public WitherCloakSword() {
        super(new ItemStack(Material.STONE_SWORD), "Wither Cloak Sword", 190, 135, 0, 0, 250, 0, 0, null,
                Collections.singletonList(new SbAbility("Creeper Veil", AbilityType.RIGHT_CLICK,
                        Arrays.asList("&7Spawns a veil around you that", "&7grants you immunity from damage.",
                                "&7Costs &d20% &7of your maximum", "&7mana each time you block a hit.",
                                "&7Unable to attack while the", "&7shield is active. Click again to",
                                "&7de-activate.", "", "&7Mana is consumed on damage."))), true, SbRarity.EPIC_SWORD);
        Utils.setIntInItem(getItem(), "active", 0);
    }

    @EventHandler
    public void onUse(AbilityUseEvent event) {
        if (!event.getSbItem().equals(this)) return;
        Player player = event.getPlayer();
        /*EntityCreeper creeper = new EntityCreeper(EntityTypes.o, ((CraftWorld)player.getWorld()).getHandle());
        creeper.setInvisible(true);
        creeper.setInvulnerable(true);
        creeper.setPowered(true);
        creeper.setNoAI(true);

        List<UUID> uuids = new ArrayList<>();

        WorldServer world = ((CraftWorld)player.getWorld()).getHandle();
        if (Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "active") == 0) {
            for (int i = 0; i < 360; i += 90) {
                double radians = Math.toRadians(i);
                double x = Math.cos(radians);
                double z = Math.sin(radians);
                Location loc = player.getLocation().clone().add(x, 0, z);
                creeper.setPosition(loc.getX(), loc.getY(), loc.getZ());
                world.addEntity(creeper);
                uuids.add(creeper.getUniqueID());
            }
            map.put(player.getName(), uuids);

            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.getInstance(), () -> {

            }, 1, 10);
        } else {
            Bukkit.getScheduler().cancelTask(taskID);
            map.get(player.getName()).forEach(uuid -> Bukkit.getEntity(uuid).remove());
            Utils.setIntInItem(player.getInventory().getItemInMainHand(), "active", 0);
        }*/
    }

    @EventHandler
    public void onHit(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        if (Utils.isItemActive(player, getItem())) {
            if (sbPlayer.getMana() >= Utils.getPercent(sbPlayer.getMaxMana(), 20)) {
                event.setCancelled(true);
                sbPlayer.removeMana((int) Utils.getPercent(sbPlayer.getMaxMana(), 20));
            }
        }
    }
}
