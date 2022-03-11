package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildBaseChangeEvent
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuild
import pl.funnyskaddon.skript.getValue

@FunnyDoc
@Name("Set Guild Home")
@Description("Ustawie (zmienia) lokalizacje bazy gildii")
@Examples(
    "set \"FajnaNazwa\" guild home to (location at (100, 100, 100) in world \"world\")",
)
class GuildSetHomeEffect : GuildValueEffect<Location>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetHomeEffect::class.java,
                "set %guild%['s] (home|base) location to %location%",
                "set %string%['s] [guild] (home|base) location to %location%",
                "set %offlineplayer%['s] [guild] (home|base) location to %location%",
                "set %location%['s] [guild] (home|base) location to %location%",
                "set %block%['s] [guild] (home|base) location to %location%",
                "set %object%['s] [guild] (home|base) location to %location%",
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuild(guildExpression)
            .peek { guild ->
                event.getValue(valueExpression)
                    .peek valuePeek@{ value ->
                        val livesChangeEvent = GuildBaseChangeEvent(FunnyEvent.EventCause.CONSOLE, null, guild, value)
                        if (!SimpleEventHandler.handle(livesChangeEvent)) {
                            return@valuePeek
                        }

                        guild.setHome(value)
                    }
            }
    }

}