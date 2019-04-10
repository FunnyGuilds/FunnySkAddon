package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.BasicUtil;

public class GuildUpperPointLocation extends SimpleExpression<Location>{
    
    private Expression<Object> guild;
    
    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
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
    protected Location[] get(Event e) {
	    try {
		    return new Location[] {BasicUtil.getUpperPoint(BasicUtil.getGuild(guild.getSingle(e)))};
	    } catch(Exception ex) { return null;} 
    }
}
