package pl.funnyskaddon.skript.expressions.other;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import pl.funnyskaddon.utils.TopSorting;

public class TopPlayer extends SimpleExpression<Player>{
    
    private Expression<Integer> position;
    
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
    	position = (Expression<Integer>) expr[0];
        return true;
    }
	    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    @Override
    protected Player[] get(Event e) {
        try {
        	return new Player[] {TopSorting.getGuildTopPlayer(position.getSingle(e)).getPlayer()};
        } catch(Exception ex) {
        	return null;
        }
    }
}
