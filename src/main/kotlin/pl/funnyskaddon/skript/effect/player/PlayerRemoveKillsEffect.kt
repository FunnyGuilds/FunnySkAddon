package pl.funnyskaddon.skript.effect.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.PlayerEffect
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
                "remove %number% kill[s] from %offlineplayer%['s] [(rank|ranking)]"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val killsChangeEvent = KillsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, -value)
                        if (!SimpleEventHandler.handle(killsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.updateKills { kills -> kills + killsChangeEvent.killsChange }
                    }
            }
    }

}