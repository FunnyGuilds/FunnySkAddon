package pl.funnyskaddon.skript.conditions.guilds;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;

public class GuildCanBeAttacked extends Condition{
	
	private Expression<Object> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	guild = (Expression<Object>) expr[0];
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
	        return g.canBeAttacked();
		}catch (Exception ex) { return false;}
    }
    
}