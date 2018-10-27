package pl.funnyskaddon.skript.expressions.player;

import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import net.dzikoysk.funnyguilds.basic.guild.Guild;

public class ExpressionLoaderPlayer {
	
	public ExpressionLoaderPlayer() {
		Skript.registerExpression(PlayersInGuildRegion.class, Player.class, ExpressionType.PROPERTY, "players in guild region %object%");
        Skript.registerExpression(PlayerDeaths.class, Integer.class, ExpressionType.PROPERTY, "%player%(|'s) deaths");
        Skript.registerExpression(PlayerGuild.class, Guild.class, ExpressionType.PROPERTY, "%player%(|'s) guild");
        Skript.registerExpression(PlayerKills.class, Integer.class, ExpressionType.PROPERTY, "%player%(|'s) kills");
		Skript.registerExpression(PlayerKDR.class, Number.class, ExpressionType.PROPERTY, "%player%(|'s) kdr");
		Skript.registerExpression(PlayerPoints.class, Integer.class, ExpressionType.PROPERTY, "%player%(|'s) points");
        Skript.registerExpression(PlayerPosition.class, Integer.class, ExpressionType.PROPERTY, "%player%(|'s) top position");
	}
	
}
