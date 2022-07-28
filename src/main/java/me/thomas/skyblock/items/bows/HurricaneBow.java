package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.SbAbility;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class HurricaneBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public HurricaneBow() {
        super(new ItemStack(Material.BOW), "Hurricane Bow", 120, 50, null, Collections.singletonList(
                new SbAbility("Tempest", AbilityType.NONE, Arrays.asList("&7The more kills you get using",
                        "&7this bow the more powerful it", "&7becomes! Reach &6250 &7kills to",
                        "&7unlock its full potential.", "&7Next Upgrade: &eDouble Shot &8(&a0&7/&c20&8)",
                        "", "&7Kills: &b0"))), true, SbRarity.EPIC_BOW);
        Utils.setIntInItem(getItem(), "kills", 0);
        Utils.setIntInItem(getItem(), "upgrade", 1);
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        if (!Utils.isRightItem(player, getItem())) return;
        int upgrade = Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "upgrade");

        for (int i = 0; i < upgrade; i++) {
            Arrow arrow = player.launchProjectile(Arrow.class);
            arrow.setVelocity(player.getLocation().getDirection().multiply(1));
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) return;
        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (!Utils.isRightItem(player, getItem())) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        int kills = Utils.getIntFromItem(item, "kills");
        int upgrade = Utils.getIntFromItem(player.getInventory().getItemInMainHand(), "upgrade");
        Utils.setIntInItem(item, "kills", kills + 1);
        Utils.changeLore(item, "Kills: ", "&7Kills: &b" + kills);

        switch (kills) {
            case 20:
                Utils.changeLore(item, "Next Upgrade: ", "&7Next Upgrade: &eTriple Shot &1(&a20&7/&c50&1)");
                Utils.setIntInItem(item, "upgrade", upgrade + 1);
                break;
            case 50:
                Utils.changeLore(item, "Next Upgrade: ", "&7Next Upgrade: &eQuad Shot &1(&a50&7/&c100&1)");
                Utils.setIntInItem(item, "upgrade", upgrade + 1);
                break;
            case 100:
                Utils.changeLore(item, "Next Upgrade: ", "&7Next Upgrade: &ePenta Shot &1(&a100&7/&c250&1)");
                Utils.setIntInItem(item, "upgrade", upgrade + 1);
                break;
            case 250:
                Utils.changeLore(item, "Next Upgrade: ", "&7Next Upgrade: &cMAX");
                Utils.setIntInItem(item, "upgrade", upgrade + 1);
                break;
            default:
                break;
        }
    }
}
