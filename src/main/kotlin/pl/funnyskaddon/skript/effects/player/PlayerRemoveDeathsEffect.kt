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
@Name("Remove Deaths")
@Description("Usuwa śmierci z rankingu gracza")
@Examples(
    "remove 7 deaths from player's ranking",
)
class PlayerRemoveDeathsEffect : PlayerEffect<Number>(true) {

    companion object {
        init {
            Skript.registerEffect(
                PlayerRemoveDeathsEffect::class.java,
                "remove %number% death[s] from %offlineplayer%(|'s) [rank|ranking]"
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

                val deathsChangeEvent = DeathsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, change)
                if (!SimpleEventHandler.handle(deathsChangeEvent)) {
                    return@peek
                }
                change = deathsChangeEvent.deathsChange

                user.rank.updateDeaths { deaths -> deaths + change }
            }
    }

}