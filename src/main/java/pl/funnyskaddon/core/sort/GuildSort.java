package pl.funnyskaddon.core.sort;

import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.user.User;
import pl.funnyskaddon.core.FunnySkAddon;

import java.util.*;

public class GuildSort {
    
	public static List<Guild> sort(Set<Guild> toSort) {
        TreeSet<Guild> treeSet = new TreeSet<>(new GuildSort.ValueComparator());
        for(Guild guild : toSort){
            if(guild.getMembers().size() >= FunnySkAddon.pc.minMembersToInclude){
                treeSet.add(guild);
            }
        }
        return new ArrayList<>(treeSet);
	}

    private static class ValueComparator implements Comparator<Guild> {

        ValueComparator() {}

        @Override
        public int compare(Guild a, Guild b) {
            if (a.getRank().getPoints() < b.getRank().getPoints()) {
                return 1;
            } else {
                if (a.getRank().getPoints() == b.getRank().getPoints()) {
                    int compare = a.getName().compareTo(b.getName());

                    if (compare < 0 || compare == 0) {
                        return -1;
                    } else if (compare > 0) {
                        return 1;
                    }
                } else {
                    return -1;
                }
                return -1;
            }
        }
    }
}

