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
                "remove %number% point[s] from %offlineplayer%(|'s) [rank|ranking]"
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

                val pointsChangeEvent = PointsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, change)
                if (!SimpleEventHandler.handle(pointsChangeEvent)) {
                    return@peek
                }
                change = pointsChangeEvent.pointsChange

                user.rank.updatePoints { points -> points - change }
            }
    }

}