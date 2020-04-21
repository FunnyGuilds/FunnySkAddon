package pl.funnyskaddon.core.loaders;

import pl.funnyskaddon.skript.conditions.guild.ConditionLoaderGuild;
import pl.funnyskaddon.skript.conditions.player.ConditionLoaderPlayer;
import pl.funnyskaddon.skript.effects.guilds.EffectLoaderGuild;
import pl.funnyskaddon.skript.effects.player.EffectLoaderPlayer;
import pl.funnyskaddon.skript.effects.top.EffectLoaderTop;
import pl.funnyskaddon.skript.events.guild.EventLoaderGuild;
import pl.funnyskaddon.skript.events.player.EventLoaderPlayer;
import pl.funnyskaddon.skript.expressions.guild.ExpressionLoaderGuild;
import pl.funnyskaddon.skript.expressions.player.ExpressionLoaderPlayer;
import pl.funnyskaddon.skript.expressions.top.ExpressionLoaderTop;

public class SkriptLoaders {
	
	public static void loadExpressions() {
		new ExpressionLoaderGuild();
		new ExpressionLoaderPlayer();
		new ExpressionLoaderTop();
	}
	
	public static void loadEvents() {
		new EventLoaderGuild();
		new EventLoaderPlayer();
	}
	
	public static void loadEffects() {
		new EffectLoaderGuild();
		new EffectLoaderPlayer();
		new EffectLoaderTop();
	}
	
	public static void loadConditions() {
		new ConditionLoaderGuild();
		new ConditionLoaderPlayer();
	}
	
}
