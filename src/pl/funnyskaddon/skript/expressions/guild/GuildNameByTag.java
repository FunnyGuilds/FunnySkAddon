package pl.funnyskaddon.skript.expressions.guild;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;

public class GuildNameByTag extends SimpleExpression<String>{
    
	private Expression<String> tag;
    
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
        tag = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    
	@Override
    protected String[] get(Event e) {
		try {
	        String t = tag.getSingle(e);
	        Guild g = GuildUtils.getByTag(t);
	        String name = g.getName();
	        try {
	        	return new String[]{name};
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) {return null;}
    }
}
