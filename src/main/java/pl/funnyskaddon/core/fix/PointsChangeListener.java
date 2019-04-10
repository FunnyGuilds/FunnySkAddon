package pl.funnyskaddon.core.fix;

import net.dzikoysk.funnyguilds.event.FunnyEvent.EventCause;
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PointsChangeListener implements Listener{
	@EventHandler
	public static void onRank(final PointsChangeEvent e) {
		if(e.getEventCause() == EventCause.USER) {
			int change = e.getChange();
			Player attacker = e.getDoer().getPlayer();
			Player victim = null;
			if(!e.getDoer().equals(e.getRank().getUser())) {
				victim = e.getRank().getUser().getPlayer();
			}
			if(victim != null && attacker != null) {
				final KillPointsChangeEventFix event = new KillPointsChangeEventFix(attacker, victim, change);
				Bukkit.getServer().getPluginManager().callEvent(event);
			}
		}
	}

}
