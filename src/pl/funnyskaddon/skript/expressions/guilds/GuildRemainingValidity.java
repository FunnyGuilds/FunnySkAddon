package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;

public class GuildRemainingValidity extends SimpleExpression<Timespan>{
    
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

	@SuppressWarnings("deprecation")
	@Override
    protected Timespan[] get(Event e) {
	    try {
		    Guild g = null;
			if(guild.getSingle(e) instanceof Guild) {
		    	g = (Guild) guild.getSingle(e);
		    } else if(guild.getSingle(e) instanceof Player){
		    	g = User.get((Player) guild.getSingle(e)).getGuild();
		    } else {
		    	g = GuildUtils.getByName(guild.getSingle(e).toString());
		    }
	        try {
	        	return new Timespan[]{Timespan.fromTicks((int) (g.getValidity()/50))};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;} 
    }
}