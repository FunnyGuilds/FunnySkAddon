package pl.funnyskaddon.skript;

import pl.funnyskaddon.skript.conditions.player.ConditionLoaderPlayer;
import pl.funnyskaddon.skript.effects.guilds.EffectLoaderGuild;
import pl.funnyskaddon.skript.effects.player.EffectLoaderPlayer;
import pl.funnyskaddon.skript.expressions.guild.ExpressionLoaderGuild;

public class SkriptLoader {

    public void loadExpressions() {
        new ExpressionLoaderGuild();
    }

    public void loadEffects() {
        new EffectLoaderGuild();
        new EffectLoaderPlayer();
    }

    public void loadConditions() {
        new ConditionLoaderPlayer();
    }

}
