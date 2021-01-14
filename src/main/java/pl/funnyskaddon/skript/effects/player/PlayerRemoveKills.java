package pl.funnyskaddon.skript.effects.player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class PlayerRemoveKills extends Effect {

    private Expression<OfflinePlayer> player;
    private Expression<Number> kills;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        kills = (Expression<Number>) expr[0];
        player = (Expression<OfflinePlayer>) expr[1];
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
            int k = kills.getSingle(e).intValue();
            User u = User.get(p);
            if (u.getRank().getKills() - k < 1) {
                k = 0;
            }
            try {
                u.getRank().setKills(u.getRank().getKills() - k);
            } catch (Exception ex) {
                return;
            }
        } catch (Exception ex) {
            return;
        }
    }

}
