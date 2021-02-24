package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect

@FunnyDoc
@Name("Remove Deaths")
@Description("Usuwa śmierci z rankingu gracza")
@Examples(
    "remove 7 deaths from player's ranking",
)
class PlayerRemoveDeathsEffect : PlayerEffect<Number>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerRemoveDeathsEffect::class.java,
                "remove %number% death[s] from %offlineplayer%(|'s) [rank|ranking]"
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

        if (!SimpleEventHandler.handle(DeathsChangeEvent(FunnyEvent.EventCause.CONSOLE, user?.rank, user, -change))) {
            return
        }

        user?.rank?.deaths = user?.rank?.deaths?.minus(change)!!
    }

}