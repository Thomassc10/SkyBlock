package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.Arrays;
import java.util.Collections;

public class SilentDeath extends me.thomas.skyblock.items.SbItem implements Listener {

    public SilentDeath() {
        super(new ItemStack(Material.IRON_SWORD), "Silent Death", 85, 31, 1, 9, 0, 0, 0, null, Collections.singletonList(
                new SbAbility("Shadowstep", AbilityType.RIGHT_CLICK, Arrays.asList("&7Teleport behind the enemy you",
                        "&7are looking at, gaining &c+25❁", "&cStrenght &7for &a10 &7seconds.",
                        "&7Max range of 20 blocks. Cooldown", "&7resets on kill."), 0, 60)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (Utils.isRightClick(event.getAction())) {
            RayTraceResult trace = player.rayTraceBlocks(20);
            if (trace.getHitEntity() != null && !(trace.getHitEntity() instanceof Player)) {
                Entity entity = trace.getHitEntity();
                player.teleport(entity.getLocation().add(entity.getLocation().getDirection().multiply(-1)));
                SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
                sbPlayer.addStrenght(25);
                Utils.scheduleTask(() -> sbPlayer.removeStrenght(25), 200);
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) return;
        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (!Utils.isRightItem(player, getItem())) return;
        Utils.resetCooldown(player, getAbilities().get(0));
    }
}
