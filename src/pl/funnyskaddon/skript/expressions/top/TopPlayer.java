package pl.funnyskaddon.skript.expressions.top;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;
import pl.funnyskaddon.core.utils.TopUtil;

public class TopPlayer extends SimpleExpression<OfflinePlayer>{
    
    private Expression<Number> position;
    
    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }
	    @Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
    	position = (Expression<Number>) expr[0];
        return true;
    }
	    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    @SuppressWarnings("deprecation")
		@Override
    protected OfflinePlayer[] get(Event e) {
        try {
        	User u = TopUtil.getGuildTopPlayer(position.getSingle(e).intValue()-1);
        	if(u.getPlayer() == null) {
                return new OfflinePlayer[] {Bukkit.getServer().getOfflinePlayer(u.getName())};
            } else {
                return new OfflinePlayer[] {u.getPlayer()};
            }
        } catch(Exception ex) {
        	return null;
        }
    }
}
