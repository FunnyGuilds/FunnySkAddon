package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Set Guild Lives")
@Description("Ustawie ilość żyć gildii")
@Examples(
    "set amount \"FajnaNazwa\" guild lives to 6",
)
class GuildSetLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetLivesEffect::class.java,
                "set [(number|amount) of] %object%(|'s) guild lives to %number%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val livesChangeEvent = GuildLivesChangeEvent(FunnyEvent.EventCause.CONSOLE, null, guild, value)
                        if (!SimpleEventHandler.handle(livesChangeEvent)) {
                            return@valuePeek
                        }

                        guild.lives = livesChangeEvent.newLives
                    }
            }
    }

}