package me.thomas.skyblock.items.swords;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class EdibleMace extends me.thomas.skyblock.items.SbItem implements Listener {

    public EdibleMace() {
        super(new ItemStack(Material.MUTTON), "Edible Mace", 125, 25, null, Collections.singletonList(
                new SbAbility("ME SMASH HEAD", AbilityType.RIGHT_CLICK, Arrays.asList("&7Your next attack deals &cdouble",
                        "&cdamage &7and weakens animals,", "&7making them deal &c-35% &7damage", "&7for &a30 &7seconds.",
                        "&8Debuff  doesn't stack."), 100)), true, SbRarity.RARE_SWORD);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isRightItem(player, getItem())) return;
        if (!Utils.isRightClick(event.getAction())) return;

        Utils.setStringInEntity(player, "edible_mace", "true");
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = ((Player) event.getDamager()).getPlayer();
        if (Utils.getStringFromEntity(player, "edible_mace").equals(true)) {
            Utils.setStringInEntity(player, "edible_mace", "false");
            event.setDamage(event.getDamage() * 2);
            if (Utils.getStringFromEntity(event.getEntity(), "edible_mace").equals("false")) {
                Utils.setStringInEntity(event.getEntity(), "edible_mace", "true");
                Utils.scheduleTask(() -> {
                    Utils.setStringInEntity(event.getEntity(), "edible_mace", "false");
                }, 600);
            }
        }
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) return;
        if (Utils.getStringFromEntity(event.getDamager(), "edible_mace").equals("true")) {
            event.setDamage(event.getDamage() - Utils.getPercent(event.getDamage(), 35));
        }
    }
}
