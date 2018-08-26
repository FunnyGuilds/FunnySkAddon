package pl.funnyskaddon.skript.expressions.guilds;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerGuildMembers extends SimpleExpression<Player>{
    
	private Expression<Player> player;
    
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
        player = (Expression<Player>) expr[0];
        return true;
    }
	    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Player[] get(Event e) {
		try {
	        User u = User.get((Player) player.getSingle(e).getPlayer());
	        Guild g = u.getGuild();
	        Set<User> membersu;
	        List<Player> members = new ArrayList<>();
	        if(u.hasGuild()){
	        	if(g.getDeputies() != null){
	            	membersu = g.getMembers();
	            	for(User uu : membersu){
	            		members.add(uu.getPlayer());
	            	}
	        	} else{
	            	members = null; 
	        	}
	        } else{
	        	members = null;
	        }
	        try {
	        	return members.toArray(new Player[members.size()]);
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) { return null;}
    }
}

