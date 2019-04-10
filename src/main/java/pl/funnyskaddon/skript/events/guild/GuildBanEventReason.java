package pl.funnyskaddon.skript.events.guild;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.event.guild.GuildBanEvent;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class GuildBanEventReason extends SimpleExpression<String>{ 

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
	@Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return ScriptLoader.isCurrentEvent(GuildBanEvent.class);
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected String[] get(Event e) {
		return new String[] {((GuildBanEvent) e).getReason()};
    }
}
