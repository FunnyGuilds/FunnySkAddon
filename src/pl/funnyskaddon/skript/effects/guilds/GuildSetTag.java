package pl.funnyskaddon.skript.effects.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;

public class GuildSetTag extends Effect{
    private Expression<Object> guild;
    private Expression<String> name;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        guild = (Expression<Object>) expr[0];
        name = (Expression<String>) expr[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    @Override
    protected void execute(Event e) {
    	try {
    		Guild g = null;
		    if(guild.getSingle(e) instanceof Guild) {
	    		g = (Guild) guild.getSingle(e);
	    	} else if(guild.getSingle(e) instanceof Player){
	    		g = User.get((Player) guild.getSingle(e)).getGuild();
	    	} else {
	    		g = GuildUtils.getByName(guild.getSingle(e).toString());
	    	}
	        String n = name.getSingle(e);
	        try {
	        	g.setTag(n);
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}