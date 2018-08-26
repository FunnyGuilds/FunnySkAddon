package pl.funnyskaddon.skript.expressions.players;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerKills extends SimpleExpression<Integer>{
    
    private Expression<Player> player;
    
    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
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
    protected Integer[] get(Event e) {
	    try {
	        User u = User.get((Player) player.getSingle(e).getPlayer());
	        try {
	        	return new Integer[]{u.getRank().getKills()};
	        } catch(Exception ex) {
	        	return null;
	        }
	    } catch(Exception ex) { return null;}
    }
}