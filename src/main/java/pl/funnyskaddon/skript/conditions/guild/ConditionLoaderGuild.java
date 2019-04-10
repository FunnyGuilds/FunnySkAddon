package pl.funnyskaddon.skript.conditions.guild;

import ch.njol.skript.Skript;

public class ConditionLoaderGuild {
	
	public ConditionLoaderGuild() {
		Skript.registerCondition(GuildDoesExist.class, "guild %string% (does|) exist(s|)",
			"guild %string% (doesn't|does not) exist(s|)");
		Skript.registerCondition(GuildIsBanned.class, "guild %object% is banned",
			"guild %object% is(n't| not) banned");
		Skript.registerCondition(GuildCanBeAttacked.class, "guild %object% can be attacked",
			"guild %object% (cannot|can't) be attacked");
		Skript.registerCondition(GuildCanBuild.class, "guild %object% can build",
			"guild %object% (cannot|can't) build");
		Skript.registerCondition(GuildPvPEnabled.class, "guild %object% pvp is (enabled|on)", 
			"guild %object% pvp is (disabled|off)");
	}
	
}
