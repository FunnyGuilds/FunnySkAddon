package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.PlayerEffect

class PlayerSetPointsEffect : PlayerEffect<Int>(false) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerSetPointsEffect::class.java,
                "guild set [(number|amount) of] %offlineplayer%(|'s) [rank|ranking] points to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getUser(event)?.rank?.points = getValue(event)!!
    }

}