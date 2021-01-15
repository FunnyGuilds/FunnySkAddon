package pl.funnyskaddon.skript.effects

import ch.njol.skript.lang.Effect
import org.bukkit.event.Event

abstract class FunnyEffect : Effect() {

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

}