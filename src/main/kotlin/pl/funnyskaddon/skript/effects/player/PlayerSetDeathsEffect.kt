package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.PlayerEffect

class PlayerSetDeathsEffect : PlayerEffect<Int>(false) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerSetDeathsEffect::class.java,
                "guild set [(number|amount) of] %offlineplayer%(|'s) [rank|ranking] deaths to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getUser(event)?.rank?.deaths = getValue(event)!!
    }

}