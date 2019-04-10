package pl.funnyskaddon.skript.effects.guilds;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.BasicUtil;

public class GuildRemoveLive extends Effect {
	
    private Expression<Object> guild;
    private Expression<Number> lives;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        guild = (Expression<Object>) expr[1];
        lives = (Expression<Number>) expr[0];
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
	        int l = lives.getSingle(e).intValue();
	        try {
	        	for(int i = 0; i < l; i++){
	        		g.removeLive();
	        	}
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}
