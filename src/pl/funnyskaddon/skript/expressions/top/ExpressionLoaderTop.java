package pl.funnyskaddon.skript.expressions.top;

import org.bukkit.OfflinePlayer;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import net.dzikoysk.funnyguilds.basic.guild.Guild;

public class ExpressionLoaderTop {
	
	public ExpressionLoaderTop() {
		Skript.registerExpression(TopGuild.class, Guild.class, ExpressionType.PROPERTY, "guild in position %number%");
		Skript.registerExpression(TopPlayer.class, OfflinePlayer.class, ExpressionType.PROPERTY, "player in position %number%");
	}
	
}
