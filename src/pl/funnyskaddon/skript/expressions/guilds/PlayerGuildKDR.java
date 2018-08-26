package pl.funnyskaddon.skript.expressions.guilds;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerGuildKDR extends SimpleExpression<Number>{
    
    private Expression<Player> player;
    
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
        player = (Expression<Player>) expr[0];
        return true;
    }
    
	    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
	    @Override
    protected Number[] get(Event e) {
	    try {
		    Guild g = User.get((Player) player.getSingle(e).getPlayer()).getGuild();
		    try {
		    	return new Float[]{g.getRank().getKDR()};
		    } catch(Exception ex) {
		        return null;
		    }
	    } catch(Exception ex) { return null;}
    }
}
