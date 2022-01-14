package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.PlayerEffect
import pl.funnyskaddon.skript.getUserOption
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Set Points")
@Description("Ustawia punkty w rankingu gracza")
@Examples(
    "set number of player's ranking points to 1050",
)
class PlayerSetPointsEffect : PlayerEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerSetPointsEffect::class.java,
                "set [(number|amount) of] %offlineplayer%(|'s) [rank|ranking] points to %number%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val pointsChangeEvent =
                            PointsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, value - user.rank.points)
                        if (!SimpleEventHandler.handle(pointsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.points = user.rank.points + pointsChangeEvent.pointsChange
                    }
            }
    }

}