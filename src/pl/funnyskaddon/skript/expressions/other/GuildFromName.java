package pl.funnyskaddon.skript.expressions.other;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;

public class GuildFromName extends SimpleExpression<Guild>{
    
    private Expression<String> name;
    
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
    	name = (Expression<String>) expr[0];
        return true;
    }
	    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    @Override
    protected Guild[] get(Event e) {
	    String n = name.getSingle(e); 
        try {
        	return new Guild[] {GuildUtils.getByName(n)};
        } catch(Exception ex) {
        	return null;
        }
    }
}
