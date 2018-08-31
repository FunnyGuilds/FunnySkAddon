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
import pl.funnyskaddon.skript.effects.guilds.GuildSetEnlarge;
import pl.funnyskaddon.skript.effects.guilds.GuildSetExpirationTime;
import pl.funnyskaddon.skript.effects.guilds.GuildSetHomeLocation;
import pl.funnyskaddon.skript.effects.guilds.GuildSetLives;
import pl.funnyskaddon.skript.effects.guilds.GuildSetLocation;
import pl.funnyskaddon.skript.effects.guilds.GuildSetName;
import pl.funnyskaddon.skript.effects.guilds.GuildSetOwner;
import pl.funnyskaddon.skript.effects.guilds.GuildSetSize;
import pl.funnyskaddon.skript.effects.guilds.GuildSetTag;
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
		Skript.registerEffect(GuildSetSize.class, "guild set size of guild %object% to %number%");
		Skript.registerEffect(GuildSetEnlarge.class, "guild set enlarge of guild %object% to %number%");
		
		Skript.registerEffect(UpdateTop.class, "update guild tops");
	}
}
