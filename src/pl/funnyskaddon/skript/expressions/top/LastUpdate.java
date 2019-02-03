package pl.funnyskaddon.skript.expressions.top;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import pl.funnyskaddon.core.utils.TopUtil;

public class LastUpdate extends SimpleExpression<Date>{
    
    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }
	    @Override
    public boolean isSingle() {
        return true;
    }
    
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }
	    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    @Override
    protected Date[] get(Event e) {
        try {
        	return new Date[]{TopUtil.lastupdate};
        } catch(Exception ex) {
        	return null;
        }
    }
}
