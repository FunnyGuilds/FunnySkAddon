package pl.funnyskaddon.skript.expressions.guild;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import pl.funnyskaddon.core.Utils;

public class GuildOwner extends SimpleExpression<OfflinePlayer>{
    
    private Expression<Object> guild;
    
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
    	guild = (Expression<Object>) expr[0];
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
		    Guild g = Utils.getGuild(guild.getSingle(e));
			if(g.getOwner().getPlayer()==null) {
				return new OfflinePlayer[] {Bukkit.getServer().getOfflinePlayer(g.getOwner().getName())};
			}
	        return new Player[]{g.getOwner().getPlayer()};
	    } catch(Exception ex) {
	        return null;
	    }
    }
}
