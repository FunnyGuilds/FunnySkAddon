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
import pl.funnyskaddon.skript.getGuild
import pl.funnyskaddon.skript.getValue

@FunnyDoc
@Name("Remove Expiration Time")
@Description("Usuwa daną ilość czasu z daty wygaśnięcia gildii")
@Examples(
    "remove 1 day from \"FajnaNazwa\" guild expiration time",
)
class GuildRemoveFromExpirationTimeEffect : GuildValueEffect<Timespan>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveFromExpirationTimeEffect::class.java,
                "remove %timespan% from %guild%['s] (expiration|validity) [time]",
                "remove %timespan% from %string%['s] [guild] (expiration|validity) [time]",
                "remove %timespan% from %offlineplayer%['s] [guild] (expiration|validity) [time]",
                "remove %timespan% from %location%['s] [guild] (expiration|validity) [time]",
                "remove %timespan% from %block%['s] [guild] (expiration|validity) [time]",
                "remove %timespan% from %object%['s] [guild] (expiration|validity) [time]"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuild(guildExpression)
            .peek { guild ->
                event.getValue(valueExpression)
                    .map(Timespan::getMilliSeconds)
                    .peek valuePeek@{ value ->
                        if (!SimpleEventHandler.handle(
                                GuildExtendValidityEvent(
                                    FunnyEvent.EventCause.CONSOLE,
                                    null,
                                    guild,
                                    -value
                                )
                            )
                        ) {
                            return@valuePeek
                        }

                        guild.validity = guild.validity - value
                    }
            }
    }

}