package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class GuildList extends SimpleExpression<Guild>{
    
    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }
    
	@Override
    public boolean isSingle() {
        return true;
    }
    
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }
    
	@Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	
	@Override
    protected Guild[] get(Event e) {
		try {
		    return GuildUtils.getGuilds().toArray(new Guild[GuildUtils.getGuilds().size()]);
		} catch(Exception ex) {return null;}
    }
}
