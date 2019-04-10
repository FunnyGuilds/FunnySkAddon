package pl.funnyskaddon.core.fix;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KillPointsChangeEventFix extends Event{
	private static final HandlerList handlers = new HandlerList();
	private Player attacker;
	private Player victim;
	private int change;

    public KillPointsChangeEventFix(Player attacker, Player victim, int change) {
        this.attacker = attacker;
        this.victim = victim;
        this.change = change;
    }

    public Player getAttacker() {
        return attacker;
    }
    
    public Player getVictim() {
        return victim;
    }
    
    public int getChange() {
    	return change;
    }
   
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
