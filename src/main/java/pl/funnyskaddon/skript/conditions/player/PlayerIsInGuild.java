package pl.funnyskaddon.skript.conditions.player;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import pl.funnyskaddon.NegationUtil;
import pl.funnyskaddon.util.GuildUtil;

import javax.annotation.Nullable;

public class PlayerIsInGuild extends Condition {

    private Expression<OfflinePlayer> player;
    private Expression<Object> guild;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        player = (Expression<OfflinePlayer>) expr[0];
        guild = (Expression<Object>) expr[1];
        setNegated(i == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    public boolean check(Event e) {
        try {
            Guild g = GuildUtil.Companion.getGuild(guild.getSingle(e));
            OfflinePlayer p = player.getSingle(e);
            for (User u : g.getMembers()) {
                if (u.getPlayer() == p) {
                    return NegationUtil.negation(true, isNegated());
                }
            }
            return NegationUtil.negation(false, isNegated());
        } catch (Exception ex) {
            return NegationUtil.negation(false, isNegated());
        }
    }

}