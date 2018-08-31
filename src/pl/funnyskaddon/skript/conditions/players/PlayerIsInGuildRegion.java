package pl.funnyskaddon.skript.conditions.players;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import pl.funnyskaddon.utils.RegionUtil;

public class PlayerIsInGuildRegion extends Condition{
	
	private Expression<Player> player;
	private Expression<Object> obj;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	player = (Expression<Player>) expr[0];
    	obj = (Expression<Object>) expr[1];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
	@Override
    public boolean check(Event e){
		try {
	    	return RegionUtil.isPlayerInGuildRegion(player.getSingle(e), getGuild(obj, e));
		}catch (Exception ex) { return false;}
    }
    
	private Guild getGuild(Expression<Object> obj, Event e){
	    Object o = obj.getSingle(e);
	    if(o instanceof Guild){
	        return (Guild) o;
	    } else if(o instanceof Player){
	        return User.get((Player) o).getGuild();
	    } else{
	        return GuildUtils.getByName(o.toString());
	    }
	}
}