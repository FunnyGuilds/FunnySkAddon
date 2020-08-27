package pl.funnyskaddon.skript.conditions.guild;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import pl.funnyskaddon.NegationUtil;
import pl.funnyskaddon.util.GuildUtil;

import javax.annotation.Nullable;

public class GuildCanBuild extends Condition {

    private Expression<Object> guild;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        guild = (Expression<Object>) expr[0];
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
            return NegationUtil.negation(GuildUtil.Companion.getGuild(guild.getSingle(e)).canBuild(), isNegated());
        } catch (Exception ex) {
            return NegationUtil.negation(false, isNegated());
        }
    }

}