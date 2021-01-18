package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect

@FunnyDoc
@Name("Set Points")
@Description("Ustawia punkty w rankingu gracza")
@Examples(
    "set number of player's ranking points to 1050",
)
class PlayerSetPointsEffect : PlayerEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerSetPointsEffect::class.java,
                "set [(number|amount) of] %offlineplayer%(|'s) [rank|ranking] points to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getUser(event)?.rank?.points = getValue(event)?.toInt()!!
    }

}