package pl.funnyskaddon.skript.expressions.guild;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;
import pl.funnyskaddon.core.FunnySkAddon;

public class MoneyToCreateGuildVip extends SimpleExpression<Double>{
    
    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }
    
    @Override
    public boolean init(Expression<?>[] expr,int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@Override
    protected Double[] get(Event e) {
		try {
			double money = FunnySkAddon.pc.requiredMoneyVip;
	    	try {
	    		return new Double[]{money};
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) { return null;}
    }
}
