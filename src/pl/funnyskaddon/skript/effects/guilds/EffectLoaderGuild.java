package pl.funnyskaddon.skript.effects.guilds;

import ch.njol.skript.Skript;

public class EffectLoaderGuild {
	
	public EffectLoaderGuild() {
		Skript.registerEffect(GuildAddDeputy.class, "guild add %offlineplayer% to deputies of guild %object%");
		Skript.registerEffect(GuildAddLive.class, "guild add %number% lives to guild %object%");
		Skript.registerEffect(GuildAddMember.class, "guild add %offlineplayer% to members of guild %object%");
		Skript.registerEffect(GuildDelete.class, "delete guild %object%");
//		Skript.registerEffect(GuildCreateGuild.class, "create guild named %string% with tag %string% at location %location% owned by %player%");
		Skript.registerEffect(GuildRemoveDeputy.class, "guild remove %offlineplayer% from deputies of guild %object%");
		Skript.registerEffect(GuildRemoveLive.class, "guild remove %number% lives from guild %object%");
		Skript.registerEffect(GuildRemoveMember.class, "guild remove %offlineplayer% from members of guild %object%");
		Skript.registerEffect(GuildSetLives.class, "guild set %number% to lives of guild %object%");
		Skript.registerEffect(GuildSetName.class, "guild set %string% to name of guild %object%");
		Skript.registerEffect(GuildSetOwner.class, "guild set %offlineplayer% to owner of guild %object%");
		Skript.registerEffect(GuildSetTag.class, "guild set %string% to tag of guild %object%");
		Skript.registerEffect(GuildSetLocation.class, "guild set location of guild %object% to %location%");
		Skript.registerEffect(GuildSetHomeLocation.class, "guild set home location of guild %object% to %location%");
		Skript.registerEffect(GuildSetExpirationTime.class, "guild set expirtation time of guild %object% to %timespan%");
		Skript.registerEffect(GuildAddToExpirationTime.class, "guild add %timespan% to expirtation time of guild %object%");
		Skript.registerEffect(GuildRemoveFromExpirationTime.class, "guild remove %timespan% from expirtation time of guild %object%");
		Skript.registerEffect(GuildSetSize.class, "guild set size of guild %object% to %number%");
		Skript.registerEffect(GuildSetEnlarge.class, "guild set enlarge of guild %object% to %number%");
	}
	
}
