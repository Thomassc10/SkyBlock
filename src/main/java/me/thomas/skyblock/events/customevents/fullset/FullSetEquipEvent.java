package me.thomas.skyblock.events.customevents.fullset;

import me.thomas.skyblock.items.SbArmor;
import me.thomas.skyblock.player.SbPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class FullSetEquipEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final SbArmor[] armorSet;
    private final SbPlayer sbPlayer;
    public FullSetEquipEvent(Player who, SbArmor[] armorSet, SbPlayer sbPlayer) {
        super(who);
        this.armorSet = armorSet;
        this.sbPlayer = sbPlayer;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancel = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public SbArmor[] getArmorSet() {
        return armorSet;
    }

    public SbPlayer getSbPlayer() {
        return sbPlayer;
    }
}
