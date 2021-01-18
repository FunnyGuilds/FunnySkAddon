package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect

@FunnyDoc
@Name("Add Kills")
@Description("Dodaje zabójstwa do rankingu gracza")
@Examples(
    "add 5 kills to player's ranking",
)
class PlayerAddKillsEffect : PlayerEffect<Number>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerAddKillsEffect::class.java,
                "add %number% kill[s] to %offlineplayer%(|'s) [rank|ranking]"
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

        user?.rank?.kills = user?.rank?.kills?.plus(change)!!
    }

}