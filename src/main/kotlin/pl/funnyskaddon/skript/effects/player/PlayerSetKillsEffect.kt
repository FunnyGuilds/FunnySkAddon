package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect

@FunnyDoc
@Name("Set Kills")
@Description("Ustawia zabójstwa w rankingu gracza")
@Examples(
    "set number of player's ranking kills to 2",
)
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