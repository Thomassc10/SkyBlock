package me.thomas.skyblock.events.customevents.abilityuse;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.items.SbAbility;
import me.thomas.skyblock.items.SbItem;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

public class AbilityUseEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final SbPlayer sbPlayer;
    private boolean cancel = false;
    private final SbItem item;
    private final SbAbility ability;
    private final boolean inCooldown;
    private final AbilityType type;
    private Map<String, HashMap<String, Long>> cooldowns;

    public AbilityUseEvent(Player who, SbPlayer sbPlayer, SbItem item, SbAbility ability, boolean inCooldown, AbilityType type, Map<String, HashMap<String, Long>> cooldowns) {
        super(who);
        this.item = item;
        this.ability = ability;
        this.inCooldown = inCooldown;
        this.type = type;
        this.cooldowns = cooldowns;
        this.sbPlayer = sbPlayer;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public SbItem getSbItem() {
        return item;
    }

    public SbAbility getAbility() {
        return ability;
    }

    public boolean isInCooldown() {
        return inCooldown;
    }

    public Map<String, HashMap<String, Long>> getCooldowns() {
        return cooldowns;
    }

    public SbPlayer getSbPlayer() {
        return sbPlayer;
    }

    public void resetCooldown(SbAbility ability) {
        cooldowns.get(getPlayer().getName()).remove(ability.getName());
    }

    public AbilityType getType() {
        return type;
    }
}
