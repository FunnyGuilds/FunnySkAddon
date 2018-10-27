package pl.funnyskaddon.skript.expressions.guild;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;
import pl.funnyskaddon.core.Utils;

public class GuildDeputies extends SimpleExpression<OfflinePlayer>{
    
	private Expression<Object> guild;
    
    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }
    
	@Override
    public boolean isSingle() {
        return true;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        guild = (Expression<Object>) expr[0];
        return true;
    }
    
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

	@SuppressWarnings("deprecation")
	@Override
    protected OfflinePlayer[] get(Event e) {
		try {
			Guild g = Utils.getGuild(guild.getSingle(e));
	        Set<User> deputiesu;
	        List<OfflinePlayer> deputies = new ArrayList<>();
	        deputiesu = g.getDeputies();
	    	for(User uu : deputiesu){
	    		if(uu.getPlayer() == null) {
	    			deputies.add(Bukkit.getServer().getOfflinePlayer(uu.getName()));
	            } else {
	            	deputies.add(uu.getPlayer());
	            }
	    		deputies.add(uu.getPlayer());
	    	}
	        try {
	        	return deputies.toArray(new OfflinePlayer[deputies.size()]);
	        } catch(Exception ex) {
	        	return null;
	        }
		} catch(Exception ex) { return null;} 
    }
}