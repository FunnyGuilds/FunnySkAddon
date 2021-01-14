package pl.funnyskaddon.skript.effects.player;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class PlayerSetGuild extends Effect {

    private Expression<OfflinePlayer> player;
    private Expression<Object> guild;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        player = (Expression<OfflinePlayer>) expr[0];
        guild = (Expression<Object>) expr[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) == null || guild.getSingle(e) == null) {
            return;
        }
        try {
            Guild g = null;
            if (guild.getSingle(e) instanceof Guild) {
                g = (Guild) guild.getSingle(e);
            } else {
                g = GuildUtils.getByName(guild.getSingle(e).toString());
            }
            OfflinePlayer p = player.getSingle(e);
            User u = User.get(p);
            u.setGuild(g);
            try {
                u.setGuild(g);
            } catch (Exception ex) {
                return;
            }
        } catch (Exception ex) {
            return;
        }
    }

}
