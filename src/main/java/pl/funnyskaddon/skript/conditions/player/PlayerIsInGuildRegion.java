package pl.funnyskaddon.skript.conditions.player;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import pl.funnyskaddon.NegationUtil;
import pl.funnyskaddon.util.GuildUtil;

import javax.annotation.Nullable;

public class PlayerIsInGuildRegion extends Condition {

    private Expression<Player> player;
    private Expression<Object> guild;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        player = (Expression<Player>) expr[0];
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
            return NegationUtil.negation(GuildUtil.Companion.isPlayerInGuildRegion(player.getSingle(e), GuildUtil.Companion.getGuild(guild.getSingle(e))), isNegated());
        } catch (Exception ex) {
            return NegationUtil.negation(false, isNegated());
        }
    }
}