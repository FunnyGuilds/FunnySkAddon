package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import pl.funnyskaddon.FunnySkAddon;

import java.util.List;

public class ItemsToEnlargeGuild extends SimpleExpression<ItemStack> {

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
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
    protected ItemStack[] get(Event e) {
        try {
            List<ItemStack> items = FunnySkAddon.Companion.getFgConfiguration().enlargeItems;
            try {
                return items.toArray(new ItemStack[items.size()]);
            } catch (Exception ex) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
