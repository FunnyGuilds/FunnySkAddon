package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerGuildPvp extends SimpleExpression<Boolean>{
    
    private Expression<Player> player;
    
    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
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
    protected Boolean[] get(Event e) {
	   	try {
	        Player p = (Player) player.getSingle(e).getPlayer();
	        User user = User.get(p);
	        boolean pvp = false;
	        if(user.hasGuild()){
	        	pvp = user.getGuild().getPvP();
	        } else{
	        	pvp = false;
	        }
	        try {
	        	return new Boolean[]{pvp};
	        } catch(Exception ex) {
	        	return null;
	        }
	   	} catch(Exception ex) { return null;}
    }
}
