package pl.funnyskaddon.skript.events.guild;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.util.SimpleExpression;
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberInviteEvent;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class GuildMemberInvitePlayer extends SimpleExpression<Player>{ 

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
    	if (!ScriptLoader.isCurrentEvent(GuildMemberInviteEvent.class)){
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
		return new Player[] {((GuildMemberInviteEvent) e).getDoer().getPlayer()};
    }
}
