package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.BasicUtil;

public class GuildAtLocation extends SimpleExpression<Guild>{
    
    private Expression<Object> loc;
    
    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }
    
	@Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        loc = (Expression<Object>) expr[0];
        return true;
    }
    
	@Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    
	@Override
    protected Guild[] get(Event e) {
	    try {
	       return BasicUtil.getGuildAtLocation(BasicUtil.getLoc(loc.getSingle(e)));
	    } catch(Exception ex) {return null;}
    }
	    
}
