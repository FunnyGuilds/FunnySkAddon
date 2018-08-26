package pl.funnyskaddon.skript.events.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.event.guild.GuildRenameEvent;

public class GuildRenameGuild extends SimpleExpression<Guild>{ 

    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
	@Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
    	if (!ScriptLoader.isCurrentEvent(GuildRenameEvent.class)){
    		return false;
    	}
        return true;
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Guild[] get(Event e) {
		return new Guild[] {((GuildRenameEvent) e).getGuild()};
    }
}
