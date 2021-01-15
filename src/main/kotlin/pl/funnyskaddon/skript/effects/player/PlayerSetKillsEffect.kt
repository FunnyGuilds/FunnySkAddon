package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.PlayerEffect

class PlayerSetKillsEffect : PlayerEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerSetKillsEffect::class.java,
                "set [(number|amount) of] %offlineplayer%(|'s) [rank|ranking] kills to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getUser(event)?.rank?.kills = getValue(event)?.toInt()!!
    }

}