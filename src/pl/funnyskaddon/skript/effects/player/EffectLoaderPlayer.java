package pl.funnyskaddon.skript.effects.player;

import ch.njol.skript.Skript;

public class EffectLoaderPlayer {

	public EffectLoaderPlayer() {
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
	}
	
}
