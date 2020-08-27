package pl.funnyskaddon.skript.conditions.guild;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import org.bukkit.event.Event;
import pl.funnyskaddon.NegationUtil;

import javax.annotation.Nullable;

public class GuildDoesExist extends Condition {

    private Expression<String> guild;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
        guild = (Expression<String>) expr[0];
        setNegated(i == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    public boolean check(Event e) {
        if (GuildUtils.getByName(guild.getSingle(e)) == null) {
            return NegationUtil.negation(false, isNegated());
        }
        return NegationUtil.negation(true, isNegated());
    }

}