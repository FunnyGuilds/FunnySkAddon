package pl.funnyskaddon.skript.effect.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.PlayerEffect
import pl.funnyskaddon.skript.getUserOption
import pl.funnyskaddon.skript.getValueOption

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
                "remove %number% point[s] from %offlineplayer%['s] [(rank|ranking)]"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val pointsChangeEvent = PointsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, -value)
                        if (!SimpleEventHandler.handle(pointsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.updatePoints { points -> points + pointsChangeEvent.pointsChange }
                    }
            }
    }

}