package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect

@FunnyDoc
@Name("Remove Kills")
@Description("Usuwa zabójstwa z rankingu gracza")
@Examples(
    "remove 7 kills from player's ranking",
)
class PlayerRemoveKillsEffect : PlayerEffect<Number>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerRemoveKillsEffect::class.java,
                "remove %number% kill[s] from %offlineplayer%(|'s) [rank|ranking]"
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

        if (!SimpleEventHandler.handle(KillsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, -change))) {
            return
        }

        user?.rank?.kills = user?.rank?.kills?.minus(change)!!
    }

}