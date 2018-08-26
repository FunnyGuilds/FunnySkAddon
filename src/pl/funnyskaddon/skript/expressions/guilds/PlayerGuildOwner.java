package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerGuildOwner extends SimpleExpression<Player>{
    
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
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    @Override
    protected Player[] get(Event e) {
	    try {
	        Player p = (Player) player.getSingle(e).getPlayer();
	        User user = User.get(p);
	        Player o;
	        if(user.hasGuild()){
	        	o = user.getGuild().getOwner().getPlayer();
	        } else{
	        	o = null;
	        }
	        try {
	        	return new Player[]{o};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;}
    }
}
