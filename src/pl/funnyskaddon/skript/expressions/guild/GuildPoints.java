package pl.funnyskaddon.skript.expressions.guild;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import pl.funnyskaddon.core.Utils;

public class GuildPoints extends SimpleExpression<Integer>{
    
    private Expression<Object> guild;
    
    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
    	guild = (Expression<Object>) expr[0];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
    @Override
    protected Integer[] get(Event e) {
	    try {
		    Guild g = Utils.getGuild(guild.getSingle(e));
	        try {
	        	return new Integer[]{g.getRank().getPoints()};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;}
    }
}
