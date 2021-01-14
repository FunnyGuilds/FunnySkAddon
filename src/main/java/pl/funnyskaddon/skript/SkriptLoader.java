package pl.funnyskaddon.skript;

import pl.funnyskaddon.skript.conditions.guild.ConditionLoaderGuild;
import pl.funnyskaddon.skript.conditions.player.ConditionLoaderPlayer;
import pl.funnyskaddon.skript.effects.guilds.EffectLoaderGuild;
import pl.funnyskaddon.skript.effects.player.EffectLoaderPlayer;
import pl.funnyskaddon.skript.expressions.guild.ExpressionLoaderGuild;

import java.util.ArrayList;
import java.util.List;

public class SkriptLoader {

    public void loadExpressions() {
        new ExpressionLoaderGuild();
        //new ExpressionLoaderPlayer();
    }

    public void loadEffects() {
        new EffectLoaderGuild();
        new EffectLoaderPlayer();
    }

    public void loadConditions() {
        new ConditionLoaderGuild();
        new ConditionLoaderPlayer();

        List<String> list = new ArrayList<>();

        list.stream().map(value -> {
            return "";
        });
    }

}
