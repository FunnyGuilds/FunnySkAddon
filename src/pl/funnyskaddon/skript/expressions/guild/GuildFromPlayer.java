package pl.funnyskaddon.skript.expressions.guild;

import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;

public class GuildFromPlayer extends SimpleExpression<Guild>{
    
    private Expression<OfflinePlayer> player;
    
    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }
    
    @Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
    	player = (Expression<OfflinePlayer>) expr[0];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
        
    @Override
    protected Guild[] get(Event e) {
	    OfflinePlayer p = player.getSingle(e); 
	    User u = User.get(p);
        try {
        	return new Guild[] {u.getGuild()};
        } catch(Exception ex) {
        	return null;
        }
    }
}
