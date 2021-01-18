package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect

@FunnyDoc
@Name("Remove Points")
@Description("Usuwa punkty z rankingu gracza")
@Examples(
    "remove 7 points from player's ranking",
)
class PlayerRemovePointsEffect : PlayerEffect<Number>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerRemovePointsEffect::class.java,
                "remove %number% point[s] from %offlineplayer%(|'s) [rank|ranking]"
            )
        }
    }

    override fun execute(event: Event?) {
        val user = getUser(event)

        var change = 0
        val value = getValue(event)
        if (value != null) {
            change = value.toInt()
        }

        user?.rank?.points = user?.rank?.points?.minus(change)!!
    }

}