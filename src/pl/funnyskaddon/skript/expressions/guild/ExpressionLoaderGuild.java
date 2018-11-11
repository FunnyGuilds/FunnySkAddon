package pl.funnyskaddon.skript.expressions.guild;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.Timespan;
import net.dzikoysk.funnyguilds.basic.guild.Guild;

public class ExpressionLoaderGuild {
	
	public ExpressionLoaderGuild() {
		Skript.registerExpression(GuildNameByTag.class, String.class, ExpressionType.PROPERTY, "guild name from tag %string%");
		Skript.registerExpression(GuildFromTag.class, Guild.class, ExpressionType.PROPERTY, "guild from tag %string%");
		Skript.registerExpression(GuildFromName.class, Guild.class, ExpressionType.PROPERTY, "guild from name %string%");
		Skript.registerExpression(GuildFromPlayer.class, Guild.class, ExpressionType.PROPERTY, "guild from player %player%");
			
		Skript.registerExpression(GuildDeaths.class, Integer.class, ExpressionType.PROPERTY, "%object% guild deaths");
		Skript.registerExpression(GuildDeputies.class, OfflinePlayer.class, ExpressionType.PROPERTY, "%object% guild deputies");
		Skript.registerExpression(GuildKDR.class, Number.class, ExpressionType.PROPERTY, "%object% guild (kdr|kills to deaths ratio)");
		Skript.registerExpression(GuildKills.class, Integer.class, ExpressionType.PROPERTY, "%object% guild kills");
		Skript.registerExpression(GuildLives.class, Integer.class, ExpressionType.PROPERTY, "%object% guild lives");
		Skript.registerExpression(GuildMembers.class, OfflinePlayer.class, ExpressionType.PROPERTY, "%object% guild members");
		Skript.registerExpression(GuildMembersAmount.class, Integer.class, ExpressionType.PROPERTY, "%object% guild members amount");
		Skript.registerExpression(GuildOnlineMembers.class, Player.class, ExpressionType.PROPERTY, "%object% guild online members");
		Skript.registerExpression(GuildOnlineMembersAmount.class, Integer.class, ExpressionType.PROPERTY, "%object% guild online members amount");
		Skript.registerExpression(GuildName.class, String.class, ExpressionType.PROPERTY, "%object% guild name");
		Skript.registerExpression(GuildOwner.class, OfflinePlayer.class, ExpressionType.PROPERTY, "%object% guild owner");
		Skript.registerExpression(GuildPoints.class, Integer.class, ExpressionType.PROPERTY, "%object% guild points");
        Skript.registerExpression(GuildPosition.class, Integer.class, ExpressionType.PROPERTY, "%object% guild top position");
        Skript.registerExpression(GuildPvp.class, Boolean.class, ExpressionType.PROPERTY, "%object% guild pvp");
        Skript.registerExpression(GuildTag.class, String.class, ExpressionType.PROPERTY, "%object% guild tag");
        Skript.registerExpression(GuildLocation.class, Location.class, ExpressionType.PROPERTY, "%object% guild endercrystal location");
        Skript.registerExpression(GuildRemainingValidity.class, Timespan.class, ExpressionType.PROPERTY, "%object% guild remaining time");
        Skript.registerExpression(GuildHomeLocation.class, Location.class, ExpressionType.PROPERTY, "%object% guild home location");
        Skript.registerExpression(GuildUpperPointLocation.class, Location.class, ExpressionType.PROPERTY, "upper point of guild region %object%");
        Skript.registerExpression(GuildLowerPointLocation.class, Location.class, ExpressionType.PROPERTY, "lower point of guild region %object%");
        Skript.registerExpression(GuildAllies.class, Guild.class, ExpressionType.PROPERTY, "guilds allied with guild %object%");
        Skript.registerExpression(GuildList.class, Guild.class, ExpressionType.PROPERTY, "all guilds");
        Skript.registerExpression(GuildAtLocation.class, Guild.class, ExpressionType.PROPERTY, "guild at %object%");
	}
	
}
