package pl.funnyskaddon.loaders;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.Timespan;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import pl.funnyskaddon.skript.expressions.guilds.GuildDeaths;
import pl.funnyskaddon.skript.expressions.guilds.GuildDeputies;
import pl.funnyskaddon.skript.expressions.guilds.GuildHomeLocation;
import pl.funnyskaddon.skript.expressions.guilds.GuildKDR;
import pl.funnyskaddon.skript.expressions.guilds.GuildKills;
import pl.funnyskaddon.skript.expressions.guilds.GuildLives;
import pl.funnyskaddon.skript.expressions.guilds.GuildLocation;
import pl.funnyskaddon.skript.expressions.guilds.GuildMembers;
import pl.funnyskaddon.skript.expressions.guilds.GuildMembersAmount;
import pl.funnyskaddon.skript.expressions.guilds.GuildName;
import pl.funnyskaddon.skript.expressions.guilds.GuildOnlineMembers;
import pl.funnyskaddon.skript.expressions.guilds.GuildOnlineMembersAmount;
import pl.funnyskaddon.skript.expressions.guilds.GuildOwner;
import pl.funnyskaddon.skript.expressions.guilds.GuildPoints;
import pl.funnyskaddon.skript.expressions.guilds.GuildPosition;
import pl.funnyskaddon.skript.expressions.guilds.GuildPvp;
import pl.funnyskaddon.skript.expressions.guilds.GuildRemainingValidity;
import pl.funnyskaddon.skript.expressions.guilds.GuildTag;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildDeaths;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildDeputies;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildKDR;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildKills;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildLives;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildMembers;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildMembersAmount;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildName;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildOnlineMembers;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildOnlineMembersAmount;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildOwner;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildPoints;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildPosition;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildPvp;
import pl.funnyskaddon.skript.expressions.guilds.PlayerGuildTag;
import pl.funnyskaddon.skript.expressions.other.GuildFromName;
import pl.funnyskaddon.skript.expressions.other.GuildFromTag;
import pl.funnyskaddon.skript.expressions.other.GuildNameByTag;
import pl.funnyskaddon.skript.expressions.other.TopGuild;
import pl.funnyskaddon.skript.expressions.other.TopPlayer;
import pl.funnyskaddon.skript.expressions.players.PlayerDeaths;
import pl.funnyskaddon.skript.expressions.players.PlayerGuild;
import pl.funnyskaddon.skript.expressions.players.PlayerKDR;
import pl.funnyskaddon.skript.expressions.players.PlayerKills;
import pl.funnyskaddon.skript.expressions.players.PlayerPoints;
import pl.funnyskaddon.skript.expressions.players.PlayerPosition;

public class ExpressionsLoader {
	public static void load() {
		Skript.registerExpression(TopGuild.class, Guild.class, ExpressionType.PROPERTY, "guild in position %integer%");
		Skript.registerExpression(TopPlayer.class, Player.class, ExpressionType.PROPERTY, "player in position %integer%");
		Skript.registerExpression(GuildNameByTag.class, String.class, ExpressionType.PROPERTY, "guild name from tag %string%");
		Skript.registerExpression(GuildFromTag.class, Guild.class, ExpressionType.PROPERTY, "guild from tag %string%");
		Skript.registerExpression(GuildFromName.class, Guild.class, ExpressionType.PROPERTY, "guild from name %string%");
		
		Skript.registerExpression(PlayerGuild.class, Guild.class, ExpressionType.PROPERTY, "%player% guild");
		Skript.registerExpression(PlayerGuildDeaths.class, Integer.class, ExpressionType.PROPERTY, "%player% guild deaths");
		Skript.registerExpression(PlayerGuildDeputies.class, Player.class, ExpressionType.PROPERTY, "%player% guild deputies");
		Skript.registerExpression(PlayerGuildKDR.class, Number.class, ExpressionType.PROPERTY, "%player% guild kdr");
		Skript.registerExpression(PlayerGuildKills.class, Integer.class, ExpressionType.PROPERTY, "%player% guild kills");
		Skript.registerExpression(PlayerGuildLives.class, Integer.class, ExpressionType.PROPERTY, "%player% guild lives");
		Skript.registerExpression(PlayerGuildMembers.class, Player.class, ExpressionType.PROPERTY, "%player% guild members");
		Skript.registerExpression(PlayerGuildMembersAmount.class, Integer.class, ExpressionType.PROPERTY, "%player% guild members amount");
		Skript.registerExpression(PlayerGuildOnlineMembers.class, Player.class, ExpressionType.PROPERTY, "%player% guild online members");
		Skript.registerExpression(PlayerGuildOnlineMembersAmount.class, Integer.class, ExpressionType.PROPERTY, "%player% guild online members amount");
		Skript.registerExpression(PlayerGuildName.class, String.class, ExpressionType.PROPERTY, "%player% guild name");
		Skript.registerExpression(PlayerGuildOwner.class, Player.class, ExpressionType.PROPERTY, "%player% guild owner");
		Skript.registerExpression(PlayerGuildPoints.class, Integer.class, ExpressionType.PROPERTY, "%player% guild points");
        Skript.registerExpression(PlayerGuildPosition.class, Integer.class, ExpressionType.PROPERTY, "%player% guild position");
        Skript.registerExpression(PlayerGuildPvp.class, Boolean.class, ExpressionType.PROPERTY, "%player% guild pvp");
		Skript.registerExpression(PlayerGuildTag.class, String.class, ExpressionType.PROPERTY, "%player% guild tag");
		
		Skript.registerExpression(GuildDeaths.class, Integer.class, ExpressionType.PROPERTY, "%object% guild deaths");
		Skript.registerExpression(GuildDeputies.class, Player.class, ExpressionType.PROPERTY, "%object% guild deputies");
		Skript.registerExpression(GuildKDR.class, Number.class, ExpressionType.PROPERTY, "%object% guild kdr");
		Skript.registerExpression(GuildKills.class, Integer.class, ExpressionType.PROPERTY, "%object% guild kills");
		Skript.registerExpression(GuildLives.class, Integer.class, ExpressionType.PROPERTY, "%object% guild lives");
		Skript.registerExpression(GuildMembers.class, Player.class, ExpressionType.PROPERTY, "%object% guild members");
		Skript.registerExpression(GuildMembersAmount.class, Integer.class, ExpressionType.PROPERTY, "%object% guild members amount");
		Skript.registerExpression(GuildOnlineMembers.class, Player.class, ExpressionType.PROPERTY, "%object% guild online members");
		Skript.registerExpression(GuildOnlineMembersAmount.class, Integer.class, ExpressionType.PROPERTY, "%object% guild online members amount");
		Skript.registerExpression(GuildName.class, String.class, ExpressionType.PROPERTY, "%object% guild name");
		Skript.registerExpression(GuildOwner.class, Player.class, ExpressionType.PROPERTY, "%object% guild owner");
		Skript.registerExpression(GuildPoints.class, Integer.class, ExpressionType.PROPERTY, "%object% guild points");
        Skript.registerExpression(GuildPosition.class, Integer.class, ExpressionType.PROPERTY, "%object% guild top position");
        Skript.registerExpression(GuildPvp.class, Boolean.class, ExpressionType.PROPERTY, "%object% guild pvp");
        Skript.registerExpression(GuildTag.class, String.class, ExpressionType.PROPERTY, "%object% guild tag");
        Skript.registerExpression(GuildLocation.class, Location.class, ExpressionType.PROPERTY, "%object% guild endercrystal location");
        Skript.registerExpression(GuildRemainingValidity.class, Timespan.class, ExpressionType.PROPERTY, "%object% guild remaining time");
        Skript.registerExpression(GuildHomeLocation.class, Location.class, ExpressionType.PROPERTY, "%object% guild home location");
		
		Skript.registerExpression(PlayerDeaths.class, Integer.class, ExpressionType.PROPERTY, "%player% deaths");
		Skript.registerExpression(PlayerKills.class, Integer.class, ExpressionType.PROPERTY, "%player% kills");
		Skript.registerExpression(PlayerKDR.class, Number.class, ExpressionType.PROPERTY, "%player% kdr");
		Skript.registerExpression(PlayerPoints.class, Integer.class, ExpressionType.PROPERTY, "%player% points");
        Skript.registerExpression(PlayerPosition.class, Integer.class, ExpressionType.PROPERTY, "%player% top position");
	}
}
