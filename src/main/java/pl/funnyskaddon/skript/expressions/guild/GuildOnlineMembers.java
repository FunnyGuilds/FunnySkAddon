package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.BasicUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuildOnlineMembers extends SimpleExpression<Player>{
    
	private Expression<Object> guild;
    
    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        guild = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Player[] get(Event e) {
		try {
		    Guild g = BasicUtil.getGuild(guild.getSingle(e));
	        Set<User> deputiesu;
	        List<Player> deputies = new ArrayList<>();
	        deputiesu = g.getOnlineMembers();
	    	for(User uu : deputiesu){
	    		if(!deputies.contains(uu.getPlayer())) {
	    			deputies.add(uu.getPlayer());
	    		}
	    	}
	    	try {
	    		return deputies.toArray(new Player[deputies.size()]);
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) { return null;}
    }
}
