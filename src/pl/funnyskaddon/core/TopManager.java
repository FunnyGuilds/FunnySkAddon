package pl.funnyskaddon.core;

import java.util.ArrayList;
import java.util.List;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import net.dzikoysk.funnyguilds.basic.user.UserUtils;
import pl.funnyskaddon.core.sort.GuildSort;
import pl.funnyskaddon.core.sort.UserSort;

public class TopManager {
	
	public static List<Guild> guilds = new ArrayList<>();
	public static List<User> users = new ArrayList<>();
	
	public static void update() {
		users = UserSort.sort(UserUtils.getUsers());
		guilds = GuildSort.sort(GuildUtils.getGuilds());
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
