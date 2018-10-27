package pl.funnyskaddon.skript.conditions.guild;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import pl.funnyskaddon.core.Utils;

public class GuildDoesExist extends Condition{
	
    private Expression<String> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        guild = (Expression<String>) expr[0];
        setNegated(i==1);
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
    @Override
    public boolean check(Event e){
        if(GuildUtils.getByName(guild.getSingle(e)) == null) {
        	return Utils.negation(false, isNegated());
        }
        return Utils.negation(true, isNegated());
    }
    
}