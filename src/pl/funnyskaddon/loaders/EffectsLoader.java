package pl.funnyskaddon.loaders;

import ch.njol.skript.Skript;
import pl.funnyskaddon.skript.effects.guilds.GuildAddDeputy;
import pl.funnyskaddon.skript.effects.guilds.GuildAddLive;
import pl.funnyskaddon.skript.effects.guilds.GuildAddMember;
import pl.funnyskaddon.skript.effects.guilds.GuildAddToExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.GuildDelete;
import pl.funnyskaddon.skript.effects.guilds.GuildRemoveDeputy;
import pl.funnyskaddon.skript.effects.guilds.GuildRemoveFromExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.GuildRemoveLive;
import pl.funnyskaddon.skript.effects.guilds.GuildRemoveMember;
import pl.funnyskaddon.skript.effects.guilds.GuildSetExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.GuildSetHomeLocation;
import pl.funnyskaddon.skript.effects.guilds.GuildSetLives;
import pl.funnyskaddon.skript.effects.guilds.GuildSetLocation;
import pl.funnyskaddon.skript.effects.guilds.GuildSetName;
import pl.funnyskaddon.skript.effects.guilds.GuildSetOwner;
import pl.funnyskaddon.skript.effects.guilds.GuildSetTag;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildAddDeputy;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildAddLive;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildAddMember;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildAddToExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildDelete;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildRemoveDeputy;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildRemoveFromExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildRemoveLive;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildRemoveMember;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetHomeLocation;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetLives;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetLocation;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetName;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetOwner;
import pl.funnyskaddon.skript.effects.guilds.PlayerGuildSetTag;
import pl.funnyskaddon.skript.effects.other.UpdateTop;
import pl.funnyskaddon.skript.effects.players.PlayerAddDeaths;
import pl.funnyskaddon.skript.effects.players.PlayerAddKills;
import pl.funnyskaddon.skript.effects.players.PlayerAddPoints;
import pl.funnyskaddon.skript.effects.players.PlayerRemoveDeaths;
import pl.funnyskaddon.skript.effects.players.PlayerRemoveKills;
import pl.funnyskaddon.skript.effects.players.PlayerRemovePoints;
import pl.funnyskaddon.skript.effects.players.PlayerSetDeaths;
import pl.funnyskaddon.skript.effects.players.PlayerSetGuild;
import pl.funnyskaddon.skript.effects.players.PlayerSetKills;
import pl.funnyskaddon.skript.effects.players.PlayerSetPoints;

public class EffectsLoader {
	public static void load() {
		Skript.registerEffect(PlayerAddDeaths.class, "guild add %number% deaths for %player%");
		Skript.registerEffect(PlayerAddKills.class, "guild add %number% kills for %player%");
		Skript.registerEffect(PlayerAddPoints.class, "guild add %number% points for %player%");
		Skript.registerEffect(PlayerRemovePoints.class, "guild remove %number% points from %player%");
		Skript.registerEffect(PlayerRemoveDeaths.class, "guild remove %number% deaths from %player%");
		Skript.registerEffect(PlayerRemoveKills.class, "guild remove %number% kills from %player%");
		Skript.registerEffect(PlayerSetDeaths.class, "guild set %player% deaths to %number%");
		Skript.registerEffect(PlayerSetKills.class, "guild set %player% kills to %number%");
		Skript.registerEffect(PlayerSetPoints.class, "guild set %player% points to %number%");
		Skript.registerEffect(PlayerSetGuild.class, "guild set %player% guild to %object%");
		
		Skript.registerEffect(GuildAddDeputy.class, "guild add %player% to deputies of guild %object%");
		Skript.registerEffect(GuildAddLive.class, "guild add %number% lives to guild %object%");
		Skript.registerEffect(GuildAddMember.class, "guild add %player% to members of guild %object%");
		Skript.registerEffect(GuildDelete.class, "delete guild %object%");
//		Skript.registerEffect(GuildCreateGuild.class, "create guild named %string% with tag %string% at location %location% owned by %player%");
		Skript.registerEffect(GuildRemoveDeputy.class, "guild remove %player% from deputies of guild %object%");
		Skript.registerEffect(GuildRemoveLive.class, "guild remove %number% lives from guild %object%");
		Skript.registerEffect(GuildRemoveMember.class, "guild remove %player% from members of guild %object%");
		Skript.registerEffect(GuildSetLives.class, "guild set %number% to lives of guild %object%");
		Skript.registerEffect(GuildSetName.class, "guild set %string% to name of guild %object%");
		Skript.registerEffect(GuildSetOwner.class, "guild set %player% to owner of guild %object%");
		Skript.registerEffect(GuildSetTag.class, "guild set %string% to tag of guild %object%");
		Skript.registerEffect(GuildSetLocation.class, "guild set location of guild %object% to %location%");
		Skript.registerEffect(GuildSetHomeLocation.class, "guild set home location of guild %object% to %location%");
		Skript.registerEffect(GuildSetExpirationTime.class, "guild set expirtation time of guild %object% to %timespan%");
		Skript.registerEffect(GuildAddToExpirationTime.class, "guild add %timespan% to expirtation time of guild %object%");
		Skript.registerEffect(GuildRemoveFromExpirationTime.class, "guild remove %timespan% from expirtation time of guild %object%");
		
		Skript.registerEffect(PlayerGuildAddDeputy.class, "guild add %player% to deputies of guild %player%");
		Skript.registerEffect(PlayerGuildAddLive.class, "guild add %number% lives to guild %player%");
		Skript.registerEffect(PlayerGuildAddMember.class, "guild add %player% to members of guild %player%");
		Skript.registerEffect(PlayerGuildDelete.class, "delete guild %player%");
		Skript.registerEffect(PlayerGuildRemoveDeputy.class, "guild remove %player% from deputies of guild %player%");
		Skript.registerEffect(PlayerGuildRemoveLive.class, "guild remove %number% lives from guild %player%");
		Skript.registerEffect(PlayerGuildRemoveMember.class, "guild remove %player% from members of guild %player%");
		Skript.registerEffect(PlayerGuildSetLives.class, "guild set %number% to lives of guild %player%");
		Skript.registerEffect(PlayerGuildSetName.class, "guild set %string% to name of guild %player%");
		Skript.registerEffect(PlayerGuildSetOwner.class, "guild set %player% to owner of guild %player%");
		Skript.registerEffect(PlayerGuildSetTag.class, "guild set %string% to tag of guild %player%");
		Skript.registerEffect(PlayerGuildSetLocation.class, "guild set location of guild %player% to %location%");
		Skript.registerEffect(PlayerGuildSetHomeLocation.class, "guild set home location of guild %player% to %location%");
		Skript.registerEffect(PlayerGuildSetExpirationTime.class, "guild set expirtation time of guild %player% to %timespan%");
		Skript.registerEffect(PlayerGuildAddToExpirationTime.class, "guild add %timespan% to expirtation time of guild %player%");
		Skript.registerEffect(PlayerGuildRemoveFromExpirationTime.class, "guild remove %timespan% from expirtation time of guild %player%");
		
		Skript.registerEffect(UpdateTop.class, "update guild tops");
	}
}
