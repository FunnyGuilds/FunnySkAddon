package pl.funnyskaddon.skript.events.player;

import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import pl.funnyskaddon.core.fix.KillPointsChangeEventFix;

public class EventLoaderPlayer {
	public EventLoaderPlayer() {
		Skript.registerEvent("guild revoke ally invitation", SimpleEvent.class, KillPointsChangeEventFix.class, "kill points change"); 
		Skript.registerExpression(KillPointsChangeKiller.class, Player.class, ExpressionType.SIMPLE,
				"killer");
		Skript.registerExpression(KillPointsChangeVictim.class, Player.class, ExpressionType.SIMPLE,
				"victim");
		Skript.registerExpression(KillPointsChangeChange.class, Integer.class, ExpressionType.SIMPLE,
				"change");
	}
}
