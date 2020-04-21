package pl.funnyskaddon.skript.expressions.player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.utils.TopUtil;

public class PlayerPosition extends SimpleExpression<Integer>{

	private Expression<OfflinePlayer> player;
	
	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
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
	public String toString(@Nullable Event e, boolean a) {
		return null;
	}

	@Override
	@Nullable
	protected Integer[] get(Event e) {
		try {
			User u = (User) User.get(player.getSingle(e));
			try {
				return new Integer[]{TopUtil.getPlayerPosition(u)+1};
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) { return null;}
	}

}

