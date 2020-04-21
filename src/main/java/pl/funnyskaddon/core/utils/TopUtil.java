package pl.funnyskaddon.core.utils;

import ch.njol.skript.util.Date;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import net.dzikoysk.funnyguilds.basic.user.UserUtils;
import pl.funnyskaddon.core.sort.GuildSort;
import pl.funnyskaddon.core.sort.UserSort;

import java.util.ArrayList;
import java.util.List;

public class TopUtil {
	
	public static List<Guild> guilds = new ArrayList<>();
	public static List<User> users = new ArrayList<>();
	public static Date lastupdate;
	
	public static void update() {
		users = UserSort.sort(UserUtils.getUsers());
		guilds = GuildSort.sort(GuildUtils.getGuilds());
		lastupdate = new Date();
	}
	
    public static User getGuildTopPlayer(int pos) {  
        return users.get(pos);
    }
    
    public static Guild getGuildTopGuild(int pos) {  
        return guilds.get(pos);
    }
    
    public static Integer getGuildPosition(Guild g) {
    	return guilds.indexOf(g);
    }
    
    public static Integer getPlayerPosition(User u) {
    	return users.indexOf(u);
    }
	
}
