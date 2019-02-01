package pl.funnyskaddon.skript.conditions.guild;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import pl.funnyskaddon.core.utils.BasicUtil;

public class GuildIsBanned extends Condition{
	
	private Expression<Object> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	guild = (Expression<Object>) expr[0];
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
	        return BasicUtil.negation(BasicUtil.getGuild(guild.getSingle(e)).isBanned(), isNegated());
		}catch (Exception ex) {return BasicUtil.negation(false, isNegated());}
    }
    
}