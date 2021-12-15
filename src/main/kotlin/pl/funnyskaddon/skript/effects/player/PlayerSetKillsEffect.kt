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
        val user = getUser(event)
        val rank = user?.rank
        val value = getValue(event)?.toInt()!!

        val change: Int = value - rank?.deaths!!

        if (!SimpleEventHandler.handle(DeathsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, change))) {
            return
        }

        rank.deaths = value
    }

}