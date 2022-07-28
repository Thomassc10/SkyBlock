package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.Arrays;

public class DarkClaymore extends me.thomas.skyblock.items.SbItem implements Listener {

    public DarkClaymore() {
        super(new ItemStack(Material.STONE_SWORD), "Dark Claymore", 500, 100, 0,
                0, 0, 0, 0, 0, 5, Arrays.asList("&o&7That thing was too big to be",
                        "&o&7called a sword, it was more like", "&o&7a large hunk of stone."), null, true, SbRarity.LEGENDARY_LONGSWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (Utils.isRightClick(event.getAction())) return;
        RayTraceResult rayTraceResult = player.rayTraceBlocks(5);
        if (rayTraceResult.getHitEntity() != null && rayTraceResult.getHitEntity() instanceof LivingEntity && !(rayTraceResult.getHitEntity() instanceof Player)) {
            LivingEntity entity = (LivingEntity) rayTraceResult.getHitEntity();
            entity.damage(Utils.getMeleeDamage(PlayerManager.getPlayerManager().getSBPlayer(player), Items.getSbItem(player.getInventory().getItemInMainHand()), false));
        }
    }
}
