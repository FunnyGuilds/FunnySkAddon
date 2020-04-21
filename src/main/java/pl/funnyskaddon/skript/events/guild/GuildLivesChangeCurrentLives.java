package pl.funnyskaddon.skript.events.guild;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class GuildLivesChangeCurrentLives extends SimpleExpression<Integer>{ 

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
	@Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return ScriptLoader.isCurrentEvent(GuildLivesChangeEvent.class);
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Integer[] get(Event e) {
		return new Integer[] {((GuildLivesChangeEvent) e).getNewLives()};
    }
}
