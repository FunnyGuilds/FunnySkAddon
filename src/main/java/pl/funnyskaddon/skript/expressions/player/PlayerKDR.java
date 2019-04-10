package pl.funnyskaddon.skript.expressions.player;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class PlayerKDR extends SimpleExpression<Number>{

	private Expression<OfflinePlayer> player;
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
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
	protected Number[] get(Event e) {
		try {
			User u = (User) User.get(player.getSingle(e));
			try {
				return new Float[]{u.getRank().getKDR()};
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) {return null;}
	}

}
