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
import pl.funnyskaddon.skript.getUserOption
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Set Deaths")
@Description("Ustawia śmierci w rankingu gracza")
@Examples(
    "set number of player's ranking deaths to 2",
)
class PlayerSetDeathsEffect : PlayerEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerSetDeathsEffect::class.java,
                "set [(number|amount) of] %offlineplayer%(|'s) [rank|ranking] deaths to %number%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                var value = event.getValueOption(valueExpression)
                    .orElse(0)
                    .get()
                    .toInt()

                val deathsChangeEvent =
                    DeathsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, value - user.rank.deaths)
                if (!SimpleEventHandler.handle(deathsChangeEvent)) {
                    return@peek
                }
                value = user.rank.deaths + deathsChangeEvent.deathsChange

                user.rank.deaths = value
            }
    }

}