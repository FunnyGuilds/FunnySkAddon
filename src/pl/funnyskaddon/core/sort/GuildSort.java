package pl.funnyskaddon.core.sort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.Lists;

import net.dzikoysk.funnyguilds.basic.guild.Guild;

public class GuildSort {
    
	public static List<Guild> sort(Set<Guild> guilds) {
		HashMap<Guild, Integer> map = new HashMap<Guild, Integer>();
        ValueComparatorGuild bvc = new ValueComparatorGuild(map);
        TreeMap<Guild, Integer> sorted_map = new TreeMap<Guild, Integer>(bvc);
        for(Guild g : guilds) {
            map.put(g, g.getRank().getPoints());
        }
        sorted_map.putAll(map);
        return Lists.newArrayList(sorted_map.keySet());
	}
    
}

class ValueComparatorGuild implements Comparator<Guild> {
    Map<Guild, Integer> base;

    public ValueComparatorGuild(Map<Guild, Integer> base) {
        this.base = base;
    }
    
    public int compare(Guild a, Guild b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } 
    }
}

