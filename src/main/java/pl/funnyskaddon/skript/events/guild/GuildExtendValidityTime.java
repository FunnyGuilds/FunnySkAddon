package pl.funnyskaddon.skript.events.guild;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.event.guild.GuildExtendValidityEvent;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class GuildExtendValidityTime extends SimpleExpression<Timespan>{ 

    @Override
    public Class<? extends Timespan> getReturnType() {
        return Timespan.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
	@Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return ScriptLoader.isCurrentEvent(GuildExtendValidityEvent.class);
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Timespan[] get(Event e) {
		return new Timespan[] {Timespan.fromTicks(((int) ((GuildExtendValidityEvent) e).getExtendTime())/50)};
    }
}
