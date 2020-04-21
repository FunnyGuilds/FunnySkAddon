package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.BasicUtil;

public class GuildRemainingValidityDate extends SimpleExpression<Date>{
    
    private Expression<Object> guild;
    
    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
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
    protected Date[] get(Event e) {
	    try {
		    Guild g = BasicUtil.getGuild(guild.getSingle(e));
	        try {
	        	return new Date[]{new Date(g.getValidity())};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;} 
    }
}
