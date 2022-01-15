package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.util.Timespan
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildExtendValidityEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Add To Expiration Time")
@Description("Dodaje czas do daty wygaśnięcia gildii")
@Examples(
    "add 1 day to \"FajnaNazwa\" guild expiration time",
)
class GuildAddToExpirationTimeEffect : GuildValueEffect<Timespan>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddToExpirationTimeEffect::class.java,
                "add %timespan% to %object%['s] guild (expiration|validity) [(date|time)]"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getValueOption(valueExpression)
                    .map(Timespan::getMilliSeconds)
                    .peek valuePeek@{ value ->
                        if (!SimpleEventHandler.handle(
                                GuildExtendValidityEvent(
                                    FunnyEvent.EventCause.CONSOLE,
                                    null,
                                    guild,
                                    value
                                )
                            )
                        ) {
                            return@valuePeek
                        }

                        guild.validity = guild.validity + value
                    }
            }
    }

}