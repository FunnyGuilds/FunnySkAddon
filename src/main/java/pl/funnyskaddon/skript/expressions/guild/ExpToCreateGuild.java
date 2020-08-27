package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.FunnySkAddon;

public class ExpToCreateGuild extends SimpleExpression<Integer> {

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    protected Integer[] get(Event e) {
        try {
            int xp = FunnySkAddon.Companion.getFgConfiguration().requiredExperience;
            try {
                return new Integer[]{xp};
            } catch (Exception ex) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
