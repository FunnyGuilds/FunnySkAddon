package pl.funnyskaddon.skript.conditions.players;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerIsNotInGuild extends Condition{
	
	private Expression<Player> player;
	private Expression<Object> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	player = (Expression<Player>) expr[0];
    	guild = (Expression<Object>) expr[1];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
	@Override
    public boolean check(Event e){
		if(player.getSingle(e) == null || guild.getSingle(e) == null) {
			return false;
		}
		Guild g = getGuild(guild, e);
		if(g == null) {
			return false;
		}
	    Player p = player.getSingle(e);
	    for(User u : g.getMembers()) {
	    	if(u.getPlayer() == p) {
	    		return false;
	    	}
	    }
	    return true;
    }
    
	private Guild getGuild(Expression<Object> o, Event e) {
		Guild g = null;
		try {
	    	if(o.getSingle(e) instanceof Guild) {
	    		g = (Guild) o.getSingle(e);
	    	} else {
	    		g = GuildUtils.getByName(o.getSingle(e).toString());
	    	}
		} catch(Exception ex) {}
    	return g;
	}
}