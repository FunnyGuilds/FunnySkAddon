package pl.funnyskaddon.skript.effects.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;

public class PlayerRemoveKills extends Effect{
    private Expression<OfflinePlayer> player;
    private Expression<Number> kills;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        player = (Expression<OfflinePlayer>) expr[0];
        kills = (Expression<Number>) expr[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }
    @Override
    protected void execute(Event e) {
    	try {
    		OfflinePlayer p = player.getSingle(e);
	        int po = kills.getSingle(e).intValue();
	        User u = User.get(p);
	        try {
	        	u.getRank().setKills(u.getRank().getKills()-po);
	        } catch(Exception ex) {
	        	return;
	        }
    	} catch(Exception ex) { return;}
    }
}
