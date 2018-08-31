package pl.funnyskaddon.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.guild.Region;

public class RegionUtil {
	
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
}	
