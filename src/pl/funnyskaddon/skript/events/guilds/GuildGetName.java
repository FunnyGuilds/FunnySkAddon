package pl.funnyskaddon.skript.events.guilds;

import org.bukkit.event.Event;

import ch.njol.skript.lang.util.SimpleExpression;
import net.dzikoysk.funnyguilds.basic.guild.Guild;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class GuildGetName extends SimpleExpression<String>{ 

	private Expression<Guild> guild;
	
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
		guild = (Expression<Guild>) expr[0];
        return true;
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected String[] get(Event e) {
		return new String[] {guild.getSingle(e).getName()};
    }
}
