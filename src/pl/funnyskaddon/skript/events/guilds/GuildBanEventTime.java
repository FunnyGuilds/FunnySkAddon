package pl.funnyskaddon.skript.events.guilds;

import org.bukkit.event.Event;

import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import net.dzikoysk.funnyguilds.event.guild.GuildBanEvent;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class GuildBanEventTime extends SimpleExpression<Timespan>{ 

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
    	if (!ScriptLoader.isCurrentEvent(GuildBanEvent.class)){
    		return false;
    	}
        return true;
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@SuppressWarnings("deprecation")
	@Override
    protected Timespan[] get(Event e) {
		return new Timespan[] {Timespan.fromTicks(((int) ((GuildBanEvent) e).getTime())/50)};
    }
}
