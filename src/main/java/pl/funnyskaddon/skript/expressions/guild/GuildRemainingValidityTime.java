package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.BasicUtil;

import java.util.Date;

public class GuildRemainingValidityTime extends SimpleExpression<Timespan>{
    
    private Expression<Object> guild;
    
    @Override
    public Class<? extends Timespan> getReturnType() {
        return Timespan.class;
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
    protected Timespan[] get(Event e) {
	    try {
		    Guild g = BasicUtil.getGuild(guild.getSingle(e));
		    int dateDifference = (int) ((g.getValidity()-(new Date().getTime())));
	        try {
	        	return new Timespan[]{new Timespan(dateDifference)};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;} 
    }
}
