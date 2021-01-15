package pl.funnyskaddon.skript.conditions

import ch.njol.skript.lang.Condition
import org.bukkit.event.Event

abstract class FunnyCondition : Condition() {

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

}