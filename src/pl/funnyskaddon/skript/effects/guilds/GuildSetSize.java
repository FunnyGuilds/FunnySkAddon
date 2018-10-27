package pl.funnyskaddon.skript.effects.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.Region;
import pl.funnyskaddon.core.Utils;

public class GuildSetSize extends Effect {
	
    private Expression<Object> guild;
    private Expression<Number> size;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        guild = (Expression<Object>) expr[1];
        size = (Expression<Number>) expr[0];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
    @Override
    protected void execute(Event e) {
    	try {
    		Guild g = Utils.getGuild(guild.getSingle(e));
    		Region rg = g.getRegion();
    		rg.setSize((int) size.getSingle(e));
    	} catch(Exception ex) { return;}
    }
    
}
