package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;

public class GuildPvp extends SimpleExpression<Boolean>{
    
    private Expression<Object> guild;
    
    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
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
    protected Boolean[] get(Event e) {
	    try {
		    Guild g = null;
			if(guild.getSingle(e) instanceof Guild) {
				g = (Guild) guild.getSingle(e);
			} else {
				g = GuildUtils.getByName(guild.getSingle(e).toString());
			}
	        try {
	        	return new Boolean[]{g.getPvP()};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;}
    }
}
