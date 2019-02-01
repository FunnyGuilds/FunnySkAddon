package pl.funnyskaddon.skript.effects.guilds;

import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;
import pl.funnyskaddon.core.utils.BasicUtil;

public class GuildAddMember extends Effect {
	
    private Expression<Object> guild;
    private Expression<OfflinePlayer> person;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        guild = (Expression<Object>) expr[1];
        person = (Expression<OfflinePlayer>) expr[0];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    
    @Override
    protected void execute(Event e) {
    	try {
    		Guild g = BasicUtil.getGuild(guild.getSingle(e));
    		OfflinePlayer pe = person.getSingle(e);
	        try {
	        	User pi = User.get(pe);
	        	g.addMember(pi);
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}
