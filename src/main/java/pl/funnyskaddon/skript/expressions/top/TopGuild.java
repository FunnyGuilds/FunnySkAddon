package pl.funnyskaddon.skript.expressions.top;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.rank.RankManager;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

public class TopGuild extends SimpleExpression<Guild> {

    private Expression<Number> position;

    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        position = (Expression<Number>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    protected Guild[] get(Event e) {
        try {
            int guildPosition = position.getSingle(e).intValue();
            return new Guild[]{RankManager.getInstance().getGuild(guildPosition)};
        } catch (Exception ex) {
            return null;
        }
    }
}

