package pl.funnyskaddon.skript.effects.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerGuildSetExpirationTime extends Effect{
    private Expression<Player> pl;
    private Expression<Timespan> time;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        pl = (Expression<Player>) expr[1];
        time = (Expression<Timespan>) expr[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    @Override
    protected void execute(Event e) {
    	try {
	    	Guild g = User.get(pl.getSingle(e)).getGuild();
	        try {
	        	g.setValidity(time.getSingle(e).getMilliSeconds());
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}