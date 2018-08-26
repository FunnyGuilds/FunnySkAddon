package pl.funnyskaddon.skript.events.guilds;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.util.SimpleExpression;
import net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class GuildAcceptAllyInvitationDoer extends SimpleExpression<Player>{ 

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
	@Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
    	if (!ScriptLoader.isCurrentEvent(GuildAcceptAllyInvitationEvent.class)){
    		return false;
    	}
        return true;
    }
	
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Player[] get(Event e) {
		return new Player[] {((GuildAcceptAllyInvitationEvent) e).getDoer().getPlayer()};
    }
}
