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

public class PlayerGuildDeputies extends SimpleExpression<Player>{
    
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
        User u = User.get((Player) player.getSingle(e).getPlayer());
        Guild g = u.getGuild();
        Set<User> deputiesu;
        List<Player> deputies = new ArrayList<>();
        if(u.hasGuild()){
        	if(g.getDeputies() != null){
            	deputiesu = g.getDeputies();
            	for(User uu : deputiesu){
            		deputies.add(uu.getPlayer());
            	}
        	} else{
            	deputies = null; 
        	}
        } else{
        	deputies = null;
        }
        try {
        	return deputies.toArray(new Player[deputies.size()]);
        } catch(Exception ex) {
        	return null;
        }
    }
}
