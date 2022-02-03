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
@Name("Remove Guild Lives")
@Description("Usuwa życia z gildii")
@Examples(
    "remove 4 lives from \"AC4U\" guild",
)
class GuildRemoveLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveLivesEffect::class.java,
                "remove %number% live[s] from %guild%",
                "remove %number% live[s] from %string%['s]",
                "remove %number% live[s] from %offlineplayer%['s] [guild]",
                "remove %number% live[s] from %location%['s] [guild]",
                "remove %number% live[s] from %block%['s] [guild]",
                "remove %number% live[s] from %object%['s] [guild]"
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
                            GuildLivesChangeEvent(FunnyEvent.EventCause.CONSOLE, null, guild, guild.lives - value)
                        if (!SimpleEventHandler.handle(livesChangeEvent)) {
                            return@valuePeek
                        }

                        guild.lives = livesChangeEvent.newLives
                    }
            }
    }

}