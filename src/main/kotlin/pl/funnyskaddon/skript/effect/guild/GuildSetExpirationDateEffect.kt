package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.util.Date
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildExtendValidityEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.skript.getValueOption


@FunnyDoc
@Name("Set Expiration Time")
@Description("Ustawie date wygaśnięcia gildii")
@Examples(
    "set {_tomorrow} to 1 day later",
    "set expiration date of \"FajnaNazwa\" guild to {_tomorrow}",
)
class GuildSetExpirationDateEffect : GuildValueEffect<Date>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetExpirationDateEffect::class.java,
                "set (expiration|validity) [(date|time)] of %guild% to %date%",
                "set (expiration|validity) [(date|time)] of %string% to %date%",
                "set (expiration|validity) [(date|time)] of %offlineplayer%['s] [guild] to %date%",
                "set (expiration|validity) [(date|time)] of %location%['s] [guild] to %date%",
                "set (expiration|validity) [(date|time)] of %block%['s] [guild] to %date%",
                "set (expiration|validity) [(date|time)] of %object%['s] [guild] to %date%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                event.getValueOption(valueExpression)
                    .peek valuePeek@{ value ->
                        if (!SimpleEventHandler.handle(
                                GuildExtendValidityEvent(
                                    FunnyEvent.EventCause.CONSOLE,
                                    null,
                                    guild,
                                    value.timestamp - guild.validity
                                )
                            )
                        ) {
                            return@valuePeek
                        }

                        guild.validity = value.timestamp
                    }
            }
    }

}