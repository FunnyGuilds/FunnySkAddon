package pl.funnyskaddon.skript.effects.player;

import ch.njol.skript.Skript;

public class EffectLoaderPlayer {

    public EffectLoaderPlayer() {
        Skript.registerEffect(PlayerAddDeaths.class, "guild add %number% deaths for %offlineplayer%");
        Skript.registerEffect(PlayerAddKills.class, "guild add %number% kills for %offlineplayer%");
        Skript.registerEffect(PlayerAddPoints.class, "guild add %number% points for %offlineplayer%");
        Skript.registerEffect(PlayerRemovePoints.class, "guild remove %number% points from %offlineplayer%");
        Skript.registerEffect(PlayerRemoveDeaths.class, "guild remove %number% deaths from %offlineplayer%");
        Skript.registerEffect(PlayerRemoveKills.class, "guild remove %number% kills from %offlineplayer%");
        Skript.registerEffect(PlayerSetDeaths.class, "guild set %offlineplayer% deaths to %number%");
        Skript.registerEffect(PlayerSetKills.class, "guild set %offlineplayer% kills to %number%");
        Skript.registerEffect(PlayerSetPoints.class, "guild set %offlineplayer% points to %number%");
        Skript.registerEffect(PlayerSetGuild.class, "guild set %offlineplayer% guild to %object%");
    }

}
