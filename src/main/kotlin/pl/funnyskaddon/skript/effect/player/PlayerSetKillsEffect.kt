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
                "set [(number|amount) of] %offlineplayer%['s] [(rank|ranking)] kills to %number%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val killsChangeEvent =
                            KillsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, value - user.rank.kills)
                        if (!SimpleEventHandler.handle(killsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.kills = user.rank.kills + killsChangeEvent.killsChange
                    }
            }
    }

}