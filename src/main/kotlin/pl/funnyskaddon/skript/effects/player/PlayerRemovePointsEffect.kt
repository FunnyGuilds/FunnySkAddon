package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.PlayerEffect

class PlayerRemovePointsEffect : PlayerEffect<Int>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerRemovePointsEffect::class.java,
                "remove %number% points from %offlineplayer%(|'s) [rank|ranking]"
            )
        }
    }

    override fun execute(event: Event?) {
        val user = getUser(event)

        var change = 0
        val value = getValue(event)
        if (value != null) {
            change = value
        }

        user?.rank?.points = user?.rank?.points?.minus(change)!!
    }

}