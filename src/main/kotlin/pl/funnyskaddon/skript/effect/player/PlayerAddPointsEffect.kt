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
@Name("Add Points")
@Description("Dodaje points do rankingu gracza")
@Examples(
    "add 5 points to player's ranking",
)
class PlayerAddPointsEffect : PlayerEffect<Number>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerAddPointsEffect::class.java,
                "add %number% point[s] to %offlineplayer%['s] [(rank|ranking)]"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val pointsChangeEvent = PointsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, value)
                        if (!SimpleEventHandler.handle(pointsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.updatePoints { points -> points + pointsChangeEvent.pointsChange }
                    }
            }
    }

}