package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArtisanalBow extends me.thomas.skyblock.items.SbItem implements Listener {

    private Map<UUID, Long> cooldown = new HashMap<>();
    public ArtisanalBow() {
        super(new ItemStack(Material.BOW), "Artisanal Bow", 40, 0, Arrays.asList(
                "&6Shortbow: Instantly shoots!"), null, true, SbRarity.RARE_BOW);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        event.setCancelled(true);
        if (cooldown.containsKey(player.getUniqueId())){
            if (cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                return;
            }
        }
        Arrow arrow = player.launchProjectile(Arrow.class);
        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 300);
        arrow.setVelocity(player.getLocation().getDirection().multiply(3));
    }
}
