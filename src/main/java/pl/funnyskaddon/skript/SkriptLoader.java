package pl.funnyskaddon.skript;

import pl.funnyskaddon.skript.effects.guilds.EffectLoaderGuild;
import pl.funnyskaddon.skript.effects.player.EffectLoaderPlayer;

public class SkriptLoader {

    public void loadEffects() {
        new EffectLoaderGuild();
        new EffectLoaderPlayer();
    }

}
