package pl.funnyskaddon.skript.conditions.player;

import ch.njol.skript.Skript;

public class ConditionLoaderPlayer {
	
	public ConditionLoaderPlayer() {
		Skript.registerCondition(PlayerHasGuild.class, "(player |)%player% has guild", 
			"(player |)%player% (does not|doesn't) have guild");
		Skript.registerCondition(PlayerIsDeputy.class, "(player |)%player% is deputy", 
			"(player |)%player% is(n't| not) deputy");
		Skript.registerCondition(PlayerIsOwner.class, "(player |)%player% is guild owner", 
			"(player |)%player% is(n't| not) guild owner");
		Skript.registerCondition(PlayerIsInGuildRegion.class, "(player |)%player% is in guild region %object%", 
			"(player |)%player% is(n't| not) in guild region %object%");
		Skript.registerCondition(PlayerIsInGuild.class, "(player |)%player% is in guild %object%", 
			"(player |)%player% is(n't| not) in guild %object%");
	}
	
}
