package pl.funnyskaddon.utils;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import net.dzikoysk.funnyguilds.basic.user.UserUtils;

public class TopSorting {

	public static Guild[] guildOrder = new Guild[] {null};
	public static User[] userOrder = new User[] {null};
	
	public static void update() {
		guildOrder = new Guild[GuildUtils.getGuilds().size()];
		Integer[] guildIndex = new Integer[GuildUtils.getGuilds().size()];
		userOrder = new User[UserUtils.getUsers().size()];
		Integer[] userIndex = new Integer[UserUtils.getUsers().size()];
		User uR;
		Integer iR;
		Guild gR;
		int i = 0;
        for(User u : UserUtils.getUsers()) {
        	uR = u;
        	iR = u.getRank().getPoints();
        	if(uR != null && iR != null) {
        		userOrder[i] = uR;
        		userIndex[i] = iR;
        	}
        	i++;
        }
        i = 0;
        for(Guild g : GuildUtils.getGuilds()) {
        	gR = g;
        	iR = g.getRank().getPoints();
        	if(gR != null && iR != null) {
        		guildOrder[i] = gR;
        		guildIndex[i] = iR;
        	}
        	i++;
        }
        userOrder = (User[]) bubbleSort(userIndex, userOrder);
        guildOrder = (Guild[]) bubbleSort(guildIndex, guildOrder);
	}
	
    public static User getGuildTopPlayer(int pos) {  
        try {
            return userOrder[userOrder.length-pos];
        } catch(Exception x) { return null;}
    }
    
    public static Guild getGuildTopGuild(int pos) {  
        try {
            return guildOrder[guildOrder.length-pos];
        } catch(Exception x) { return null;}
    }
    
    public static Integer getGuildPosition(Guild g) {
    	int pos = 1;
    	for(Guild gu : guildOrder) {
    		if(g == gu) {
    			return pos;
    		}
    		pos++;
    	}
    	return null;
    }
    
    public static Integer getPlayerPosition(User u) {
    	int pos = 1;
    	for(User uu : userOrder) {
    		if(u == uu) {
    			return pos;
    		}
    		pos++;
    	}
    	return null;
    }
    
    public static Object[] bubbleSort(Integer[] arr, Object[] obj) {  
		int n = arr.length;  
		int temp = 0;  
		Object tempStr = null;
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){  
				if(arr[j-1] > arr[j]){  
					temp = arr[j-1];  
					tempStr = obj[j-1];
					arr[j-1] = arr[j];  
					obj[j-1] = obj[j];
					arr[j] = temp;  
					obj[j] = tempStr;
				}                      
			}  
		}  
		return obj;
    }  
}
