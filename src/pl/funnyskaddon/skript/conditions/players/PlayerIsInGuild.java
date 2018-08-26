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

public class PlayerIsInGuild extends Condition{
	
	private Expression<Player> player;
	private Expression<Object> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	player = (Expression<Player>) expr[0];
    	guild = ( Expression<Object>) expr[1];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
	@Override
    public boolean check(Event e){
		try {
	    	Guild g = null;
	    	if(guild.getSingle(e) instanceof Guild) {
	    		g = (Guild) guild.getSingle(e);
	    	} else {
	    		g = GuildUtils.getByName(guild.getSingle(e).toString());
	    	}
	    	Player p = player.getSingle(e);
	    	for(User u : g.getMembers()) {
	    		if(u.getPlayer() == p) {
	    			return true;
	    		}
	    	}
	        return false;
		}catch (Exception ex) { return false;}
    }
    
}