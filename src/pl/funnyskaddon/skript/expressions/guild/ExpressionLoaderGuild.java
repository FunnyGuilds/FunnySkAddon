package pl.funnyskaddon.skript.expressions.guild;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.Date;
import ch.njol.skript.util.Timespan;
import net.dzikoysk.funnyguilds.basic.guild.Guild;

public class ExpressionLoaderGuild {
	
	public ExpressionLoaderGuild() {
		Skript.registerExpression(GuildNameByTag.class, String.class, ExpressionType.PROPERTY, "guild name from tag %string%");
		Skript.registerExpression(GuildFromTag.class, Guild.class, ExpressionType.PROPERTY, "guild from tag %string%");
		Skript.registerExpression(GuildFromName.class, Guild.class, ExpressionType.PROPERTY, "guild from name %string%");
		Skript.registerExpression(GuildFromPlayer.class, Guild.class, ExpressionType.PROPERTY, "guild from player %offlineplayer%");
			
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
        Skript.registerExpression(GuildRemainingValidityDate.class, Date.class, ExpressionType.PROPERTY, "%object% guild remaining date");
        Skript.registerExpression(GuildRemainingValidityTime.class, Timespan.class, ExpressionType.PROPERTY, "%object% guild remaining time");
        Skript.registerExpression(GuildHomeLocation.class, Location.class, ExpressionType.PROPERTY, "%object% guild home location");
        Skript.registerExpression(GuildUpperPointLocation.class, Location.class, ExpressionType.PROPERTY, "upper point of guild region %object%");
        Skript.registerExpression(GuildLowerPointLocation.class, Location.class, ExpressionType.PROPERTY, "lower point of guild region %object%");
        Skript.registerExpression(GuildAllies.class, Guild.class, ExpressionType.PROPERTY, "guilds allied with guild %object%");
        Skript.registerExpression(GuildList.class, Guild.class, ExpressionType.PROPERTY, "all guilds");
        Skript.registerExpression(GuildAtLocation.class, Guild.class, ExpressionType.PROPERTY, "guild at %object%");
        Skript.registerExpression(GuildBorn.class, Date.class, ExpressionType.PROPERTY, "%object% guild create time");
        Skript.registerExpression(GuildEnlargeLevel.class, Integer.class, ExpressionType.PROPERTY, "guild %object% enlarge level");
        Skript.registerExpression(GuildArea.class, Integer.class, ExpressionType.PROPERTY, "guild %object% area");
        Skript.registerExpression(ItemsToCreateGuild.class, ItemStack.class, ExpressionType.PROPERTY, "required items to create guild");
        Skript.registerExpression(ItemsToCreateGuildVip.class, ItemStack.class, ExpressionType.PROPERTY, "required items to create guild for vip");
        Skript.registerExpression(ExpToCreateGuild.class, Integer.class, ExpressionType.PROPERTY, "required exp to create guild guild");
        Skript.registerExpression(ExpToCreateGuildVip.class, Integer.class, ExpressionType.PROPERTY, "required exp to create guild for vip");
        Skript.registerExpression(MoneyToCreateGuild.class, Double.class, ExpressionType.PROPERTY, "required money to create guild guild");
        Skript.registerExpression(MoneyToCreateGuildVip.class, Double.class, ExpressionType.PROPERTY, "required money to create guild for vip");
        Skript.registerExpression(ItemsToExtensionValidity.class, ItemStack.class, ExpressionType.PROPERTY, "required items to extension validity");
        Skript.registerExpression(ItemsToTeleportHome.class, ItemStack.class, ExpressionType.PROPERTY, "required items to teleport home");
        Skript.registerExpression(ItemsToEnlargeGuild.class, ItemStack.class, ExpressionType.PROPERTY, "required items to enlarge guild");
       
	}
	
}
