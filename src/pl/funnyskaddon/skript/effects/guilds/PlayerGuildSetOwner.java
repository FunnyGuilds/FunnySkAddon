package pl.funnyskaddon.skript.effects.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerGuildSetOwner extends Effect{
    private Expression<Player> player;
    private Expression<Player> person;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        player = (Expression<Player>) expr[1];
        person = (Expression<Player>) expr[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    @Override
    protected void execute(Event e) {
    	try {
	    	Player p = (Player) player.getSingle(e).getPlayer();
	        Player pe = person.getSingle(e);
	        User u = User.get(p);
	        try {
	        	User pi = User.get(pe);
	        	u.getGuild().setOwner(pi);
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}