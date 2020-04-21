package pl.funnyskaddon.skript.effects.top;

import ch.njol.skript.Skript;

public class EffectLoaderTop {
	
	public EffectLoaderTop() {
		Skript.registerEffect(UpdateTop.class, "update guild tops");
	}
	
}
