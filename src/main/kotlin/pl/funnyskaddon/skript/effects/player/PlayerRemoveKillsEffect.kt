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
import pl.funnyskaddon.skript.getUserOption
import pl.funnyskaddon.skript.getValueOption

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

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                var change = -event.getValueOption(valueExpression)
                    .orElse(0)
                    .get()
                    .toInt()

                val killsChangeEvent = KillsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, change)
                if (!SimpleEventHandler.handle(killsChangeEvent)) {
                    return@peek
                }
                change = killsChangeEvent.killsChange

                user.rank.updateKills { kills -> kills + change }
            }
    }

}