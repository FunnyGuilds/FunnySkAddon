package pl.funnyskaddon.skript.conditions.guilds;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;

public class DoesGuildExist extends Condition{
	
    private Expression<String> guild;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        guild = (Expression<String>) expr[0];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
    @Override
    public boolean check(Event e){
        if(GuildUtils.getByName(guild.getSingle(e)) == null) {
        	return false;
        }
        return true;
    }
    
}