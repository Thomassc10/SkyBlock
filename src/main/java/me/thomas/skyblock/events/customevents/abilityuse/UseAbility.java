package me.thomas.skyblock.events.customevents.abilityuse;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.ActionSound;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.PlayerManager;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class UseAbility implements Listener {

    public Map<String, HashMap<String, Long>> cooldowns = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGH)
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        SbPlayer sbPlayer = PlayerManager.getPlayerManager().getSBPlayer(player);
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        if (!Utils.isSbItem(item)) return;
        SbItem sbItem = Items.getSbItem(item);
        if (sbItem.getAbilities() == null) return;
        SbAbility ability = sbItem.getAbilities().get(0);

        if (ability.getAbilityType() == AbilityType.RIGHT_CLICK && !Utils.isRightClick(event.getAction())) return;
        if (ability.getAbilityType() == AbilityType.LEFT_CLICK && Utils.isRightClick(event.getAction())) return;

        int manaCost = ability.getManaCost();
        int cooldown = ability.getCooldown();
        if (manaCost > 0) {
            if (sbPlayer.getMana() >= manaCost) {
                sbPlayer.removeMana(manaCost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + sbItem.getAbilities().get(0).getName());
                Bukkit.getPluginManager().callEvent(new AbilityUseEvent(player, sbPlayer, sbItem, ability, true, ability.getAbilityType(), cooldowns));
            } else {
                player.sendMessage(ChatColor.RED + "Not enough mana!");
                Utils.playSound(player, ActionSound.ERROR);
                return;
            }
        }
        if (ability.getCooldown() > 0) {
            if (cooldowns.containsKey(player.getName())) {
                if (cooldowns.get(player.getName()).get(ability.getName()) > System.currentTimeMillis()) {
                    player.sendMessage(ChatColor.RED + "This ability is currently in cooldown for " + cooldown + "s.");
                    Bukkit.getPluginManager().callEvent(new AbilityUseEvent(player, sbPlayer, sbItem, ability, true, ability.getAbilityType(), cooldowns));
                    return;
                }
            }
            if (cooldowns.containsKey(player.getName())) {
                cooldowns.get(player.getName()).put(ability.getName(), System.currentTimeMillis() + (cooldown * 1000L));
            }
            else {
                HashMap<String, Long> map = new HashMap<>();
                map.put(ability.getName(), System.currentTimeMillis() + (cooldown * 1000L));
                cooldowns.put(player.getName(), map);
            }
            Bukkit.getPluginManager().callEvent(new AbilityUseEvent(player, sbPlayer, sbItem, ability, false, ability.getAbilityType(), cooldowns));
        }
    }
}
