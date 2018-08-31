package pl.funnyskaddon.loaders;

import ch.njol.skript.Skript;
import pl.funnyskaddon.skript.conditions.guilds.DoesGuildExist;
import pl.funnyskaddon.skript.conditions.guilds.DoesNotGuildExist;
import pl.funnyskaddon.skript.conditions.guilds.GuildCanBeAttacked;
import pl.funnyskaddon.skript.conditions.guilds.GuildCanBuild;
import pl.funnyskaddon.skript.conditions.guilds.GuildCannotBeAttacked;
import pl.funnyskaddon.skript.conditions.guilds.GuildCannotBuild;
import pl.funnyskaddon.skript.conditions.guilds.GuildIsBanned;
import pl.funnyskaddon.skript.conditions.guilds.GuildIsNotBanned;
import pl.funnyskaddon.skript.conditions.guilds.GuildPvPDisabled;
import pl.funnyskaddon.skript.conditions.guilds.GuildPvPEnabled;
import pl.funnyskaddon.skript.conditions.players.PlayerHasGuild;
import pl.funnyskaddon.skript.conditions.players.PlayerHasNotGuild;
import pl.funnyskaddon.skript.conditions.players.PlayerIsDeputy;
import pl.funnyskaddon.skript.conditions.players.PlayerIsInGuild;
import pl.funnyskaddon.skript.conditions.players.PlayerIsInGuildRegion;
import pl.funnyskaddon.skript.conditions.players.PlayerIsInNotGuildRegion;
import pl.funnyskaddon.skript.conditions.players.PlayerIsNotDeputy;
import pl.funnyskaddon.skript.conditions.players.PlayerIsNotInGuild;
import pl.funnyskaddon.skript.conditions.players.PlayerIsNotOwner;
import pl.funnyskaddon.skript.conditions.players.PlayerIsOwner;

public class ConditionLoader {
	
	public static void load() {
		Skript.registerCondition(DoesGuildExist.class, "guild %string% (does|) exist(s|)");
		Skript.registerCondition(DoesNotGuildExist.class, "guild %string% (doesn't|does not) exist(s|)");
//		Skript.registerCondition(DoesGuildExist.class, "guild with tag %string% (does|) exist(s|)");
//		Skript.registerCondition(DoesNotGuildExist.class, "guild with tag %string% (doesn't|does not) exist(s|)");
		Skript.registerCondition(PlayerIsInGuild.class, "(player |)%player% is in guild %object%");
		Skript.registerCondition(PlayerIsNotInGuild.class, "(player |)%player% is(n't| not) in guild %object%");
		Skript.registerCondition(GuildIsBanned.class, "guild %object% is banned");
		Skript.registerCondition(GuildIsNotBanned.class, "guild %object% is(n't| not) banned");
		Skript.registerCondition(GuildCanBeAttacked.class, "guild %object% can be attacked");
		Skript.registerCondition(GuildCannotBeAttacked.class, "guild %object% (cannot|can't) be attacked");
		Skript.registerCondition(GuildCanBuild.class, "guild %object% can build");
		Skript.registerCondition(GuildCannotBuild.class, "guild %object% (cannot|can't) build");
		Skript.registerCondition(GuildPvPEnabled.class, "guild %object% pvp is (enabled|on)");
		Skript.registerCondition(GuildPvPDisabled.class, "guild %object% pvp is (disabled|off)");
		Skript.registerCondition(PlayerHasGuild.class, "(player |)%player% has guild");
		Skript.registerCondition(PlayerHasNotGuild.class, "(player |)%player% (does not|doesn't) have guild");
		Skript.registerCondition(PlayerIsDeputy.class, "(player |)%player% is deputy");
		Skript.registerCondition(PlayerIsNotDeputy.class, "(player |)%player% is(n't| not) deputy");
		Skript.registerCondition(PlayerIsOwner.class, "(player |)%player% is guild owner");
		Skript.registerCondition(PlayerIsNotOwner.class, "(player |)%player% is(n't| not) guild owner");
		Skript.registerCondition(PlayerIsInGuildRegion.class, "(player |)%player% is in guild region %object%");
		Skript.registerCondition(PlayerIsInNotGuildRegion.class, "(player |)%player% is(n't| not) in guild region %object%");

	}
}
