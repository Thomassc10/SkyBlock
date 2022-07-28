package me.thomas.skyblock.items.bows;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class MosquitoBow extends me.thomas.skyblock.items.SbItem implements Listener {

    public MosquitoBow() {
        super(new ItemStack(Material.BOW), "Mosquito Bow", 251, 151, 0, 39, 0, 0, 0, 0, null, Collections.singletonList(
                new SbAbility("Nasty Bite", AbilityType.NONE, Arrays.asList("&8Fully charged shots while sneaking",
                        "&7Costs &b11% &7of max mana.", "&7Deal &c+19% &7damage.", "&7Heal for &a2x &7the mana cost."))), true, SbRarity.LEGENDARY_BOW);
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (!Utils.isRightItem(player, getItem())) return;
        if (event.getForce() == 1 && player.isSneaking()) {
            SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
            int manaCost = (int) Utils.getPercent(sbPlayer.getMaxMana(), 11);
            if (sbPlayer.getMana() >= manaCost) {
                sbPlayer.regenHealth(manaCost * 2);
                sbPlayer.removeMana(manaCost);
                Utils.setIntInEntity(event.getProjectile(), "mosquito", 1);
            }
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
        if (!(event.getHitEntity() instanceof LivingEntity)) return;
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) event.getEntity().getShooter();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        SbItem sbItem = Items.getSbItem(player.getInventory().getItemInMainHand());
        if (Utils.getIntFromEntity(event.getEntity(), "mosquito") == 1) {
            ((LivingEntity)event.getHitEntity()).damage(Utils.getPercent(Utils.getMeleeDamage(sbPlayer, sbItem, false), 19));
        }
    }
}
