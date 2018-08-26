package pl.funnyskaddon.skript.events.guilds;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
 
public class GuildCreateEventFix extends Event{
	
	private static final HandlerList handlers = new HandlerList();
	private Player p;
	private Guild g;

    public GuildCreateEventFix(Player p, String tag) {
        this.p = p;
        this.g = GuildUtils.getByTag(tag);
    }

    public Player getPlayer() {
        return p;
    }
    
    public Guild getGuild() {
        return g;
    }
    

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}