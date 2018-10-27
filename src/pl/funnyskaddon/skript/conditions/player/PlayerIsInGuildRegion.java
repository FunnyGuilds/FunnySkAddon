package pl.funnyskaddon.skript.conditions.player;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import pl.funnyskaddon.core.Utils;

public class PlayerIsInGuildRegion extends Condition{
	
	private Expression<Player> player;
	private Expression<Object> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	player = (Expression<Player>) expr[0];
    	guild = (Expression<Object>) expr[1];
    	setNegated(i==1);
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
	@Override
    public boolean check(Event e){
		try {
	    	return Utils.negation(Utils.isPlayerInGuildRegion(player.getSingle(e), Utils.getGuild(guild.getSingle(e))), isNegated());
		}catch (Exception ex) {return Utils.negation(false, isNegated());}
    }
}