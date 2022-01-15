package pl.funnyskaddon.skript.effect.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.PlayerEffect
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
                "remove %number% death[s] from %offlineplayer%['s] [(rank|ranking)]"
            )
        }
    }

    override fun execute(event: Event) {
        event.getUserOption(playerExpression)
            .peek { user ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val deathsChangeEvent = DeathsChangeEvent(FunnyEvent.EventCause.CONSOLE, user, user, -value)
                        if (!SimpleEventHandler.handle(deathsChangeEvent)) {
                            return@valuePeek
                        }

                        user.rank.updateDeaths { deaths -> deaths + deathsChangeEvent.deathsChange }
                    }
            }
    }

}