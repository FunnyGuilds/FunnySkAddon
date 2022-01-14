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

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val killsChangeEvent = KillsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, value)
                        if (!SimpleEventHandler.handle(killsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.updateKills { kills -> kills + killsChangeEvent.killsChange }
                    }
            }
    }

}