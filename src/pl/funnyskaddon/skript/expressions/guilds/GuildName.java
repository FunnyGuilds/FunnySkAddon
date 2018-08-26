package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;

public class GuildName extends SimpleExpression<String>{
    
    private Expression<String> guild;
    
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
	@Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
    	guild = (Expression<String>) expr[0];
        return true;
    }
	@Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	@Override
    protected String[] get(Event e) {
		try {
		    String s = guild.getSingle(e);
	        Guild g = Guild.getOrCreate(s);
	        try {
	        	return new String[]{g.getName()};
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) { return null;}
    }
}
