package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Add Guild Lives")
@Description("Dodaje życia do gildii")
@Examples(
    "add 4 lives to \"AC4U\" guild",
)
class GuildAddLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddLivesEffect::class.java,
                "add %number% live[s] to %guild%",
                "add %number% live[s] to %string%",
                "add %number% live[s] to %offlineplayer%['s] guild",
                "add %number% live[s] to %location%['s] guild",
                "add %number% live[s] to %block%['s] guild",
                "add %number% live[s] to %object%['s] guild"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getValueOption(valueExpression)
                    .map(Number::toInt)
                    .peek valuePeek@{ value ->
                        val livesChangeEvent =
                            GuildLivesChangeEvent(FunnyEvent.EventCause.CONSOLE, null, guild, guild.lives + value)
                        if (!SimpleEventHandler.handle(livesChangeEvent)) {
                            return@valuePeek
                        }

                        guild.lives = livesChangeEvent.newLives
                    }
            }
    }

}