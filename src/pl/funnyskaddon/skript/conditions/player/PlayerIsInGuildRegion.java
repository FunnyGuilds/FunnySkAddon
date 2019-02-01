package pl.funnyskaddon.skript.conditions.player;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import pl.funnyskaddon.core.utils.BasicUtil;

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
	    	return BasicUtil.negation(BasicUtil.isPlayerInGuildRegion(player.getSingle(e), BasicUtil.getGuild(guild.getSingle(e))), isNegated());
		}catch (Exception ex) {return BasicUtil.negation(false, isNegated());}
    }
}