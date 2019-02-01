package pl.funnyskaddon.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.guild.Region;
import net.dzikoysk.funnyguilds.basic.user.User;

public class BasicUtil {
	
	public static Location getLowerPoint(Guild g) {
		Region rg = g.getRegion();
		return new Location(rg.getWorld(),rg.getLowerX(),  rg.getLowerY(), rg.getLowerZ());
	}
	
	public static Location getUpperPoint(Guild g) {
		Region rg = g.getRegion();
		return new Location(rg.getWorld(),rg.getUpperX(),  rg.getUpperY(), rg.getUpperZ());
	}
	
	public static boolean isPlayerInGuildRegion(Player p, Guild g) {
		Location pLoc = p.getLocation();
		Region rg = g.getRegion();
		if(((pLoc.getX() > rg.getUpperX() && pLoc.getX() < rg.getLowerX()) || (pLoc.getX() < rg.getUpperX() && pLoc.getX() > rg.getLowerX()))
		&& ((pLoc.getY() > rg.getUpperY() && pLoc.getY() < rg.getLowerY()) || (pLoc.getY() < rg.getUpperY() && pLoc.getY() > rg.getLowerY()))
		&& ((pLoc.getZ() > rg.getUpperZ() && pLoc.getZ() < rg.getLowerZ()) || (pLoc.getZ() < rg.getUpperZ() && pLoc.getZ() > rg.getLowerZ()))) {
			return true;
		}
		return false;
	}
	
	public static Player[] getPlayersInGuildRegion(Guild g) {
		Region rg = g.getRegion();
		List<Player> list = new ArrayList<>();
		for(Player p : rg.getWorld().getPlayers()) {
			if(isPlayerInGuildRegion(p, g)) {
				list.add(p);
			}
		}
		return list.toArray(new Player[list.size()]);
	}
	
	public static Guild[] getGuildAtLocation(Location pLoc) {
		for(Guild g : GuildUtils.getGuilds()) {
			Region rg = g.getRegion();
			if(((pLoc.getX() > rg.getUpperX() && pLoc.getX() < rg.getLowerX()) || (pLoc.getX() < rg.getUpperX() && pLoc.getX() > rg.getLowerX()))
			&& ((pLoc.getY() > rg.getUpperY() && pLoc.getY() < rg.getLowerY()) || (pLoc.getY() < rg.getUpperY() && pLoc.getY() > rg.getLowerY()))
			&& ((pLoc.getZ() > rg.getUpperZ() && pLoc.getZ() < rg.getLowerZ()) || (pLoc.getZ() < rg.getUpperZ() && pLoc.getZ() > rg.getLowerZ()))) {
				return new Guild[] {g};
			}
		}
		return null;
	}
	
	public static Guild getGuild(Object guildObj) {
		Guild g = null;
	    if(guildObj instanceof Guild) {
    		g = (Guild) guildObj;
    	} else if(guildObj instanceof Player) {
    		g = User.get((Player) guildObj).getGuild();
    	} else {
    		try {
    			g = GuildUtils.getByName(guildObj.toString());
    		} catch(Exception ex) {}
    	}
	    return g;
	}
	
	public static Location getLoc(Object locObj) {
    	if(locObj instanceof Location) {
    		return (Location) locObj;
    	} else if(locObj instanceof Player) {
    		return ((Player) locObj).getLocation();
    	} else if(locObj instanceof Entity) {
    		return ((Entity) locObj).getLocation();
    	}else if(locObj instanceof LivingEntity) {
    		return ((LivingEntity) locObj).getLocation();
    	}
    	return null;
    }
	
	public static boolean negation(boolean subject, boolean negated) {
		if(negated) {
			return !subject;
		} 
		return subject;
	}
}
