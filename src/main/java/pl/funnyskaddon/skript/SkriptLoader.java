package pl.funnyskaddon.skript;

import pl.funnyskaddon.core.FunnySkAddon;
import pl.funnyskaddon.skript.conditions.guild.ConditionLoaderGuild;
import pl.funnyskaddon.skript.conditions.player.ConditionLoaderPlayer;
import pl.funnyskaddon.skript.effects.guilds.EffectLoaderGuild;
import pl.funnyskaddon.skript.effects.player.EffectLoaderPlayer;
import pl.funnyskaddon.skript.events.guild.EventLoaderGuild;
import pl.funnyskaddon.skript.events.player.EventLoaderPlayer;
import pl.funnyskaddon.skript.expressions.guild.ExpressionLoaderGuild;
import pl.funnyskaddon.skript.expressions.player.ExpressionLoaderPlayer;
import pl.funnyskaddon.skript.expressions.top.ExpressionLoaderTop;

public class SkriptLoader {

    private final FunnySkAddon plugin;

    public SkriptLoader(FunnySkAddon plugin) {
        this.plugin = plugin;
    }

    public void loadExpressions() {
        new ExpressionLoaderGuild();
        new ExpressionLoaderPlayer();
        new ExpressionLoaderTop();
    }

    public void loadEvents() {
        new EventLoaderGuild();
        new EventLoaderPlayer();
    }

    public void loadEffects() {
        new EffectLoaderGuild();
        new EffectLoaderPlayer();
    }

    public void loadConditions() {
        new ConditionLoaderGuild();
        new ConditionLoaderPlayer();
    }

}
