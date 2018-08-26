package pl.funnyskaddon.skript.effects.players;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerSetGuild extends Effect{
    private Expression<Player> player;
    private Expression<Object> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        guild = (Expression<Object>) expr[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    @Override
    protected void execute(Event e) {
    	if(player.getSingle(e) == null || guild.getSingle(e) == null) {
			return;
		}
    	try {
    		Guild g = null;
	    	if(guild.getSingle(e) instanceof Guild) {
	    		g = (Guild) guild.getSingle(e);
	    	} else {
	    		g = GuildUtils.getByName(guild.getSingle(e).toString());
	    	}
	    	Player p = (Player) player.getSingle(e);
	        User u = User.get(p);
	        u.setGuild(g);
	        try {
	        	u.setGuild(g);
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}
