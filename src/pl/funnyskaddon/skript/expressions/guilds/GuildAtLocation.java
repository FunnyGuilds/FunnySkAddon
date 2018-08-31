package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import pl.funnyskaddon.utils.RegionUtil;

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
	       return RegionUtil.getGuildAtLocation(getLoc(loc, e));
	    } catch(Exception ex) { return null;}
    }
	    
	    private Location getLoc(Expression<Object> obj, Event e) {
	    	Object o = obj.getSingle(e);
	    	if(o instanceof Location) {
	    		return (Location) o;
	    	} else if(o instanceof Player) {
	    		return ((Player) o).getLocation();
	    	} else {
	    		return null;
	    	}
	    }
}
