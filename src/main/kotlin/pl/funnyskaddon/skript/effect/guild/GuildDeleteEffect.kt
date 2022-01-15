package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildDeleteEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildEffect
import pl.funnyskaddon.skript.getGuildOption


@FunnyDoc
@Name("Delete Guild")
@Description("Usuwa gildie")
@Examples(
    "delete \"AC4U\" guild",
)
class GuildDeleteEffect : GuildEffect() {

    companion object {
        init {
            Skript.registerEffect(
                GuildDeleteEffect::class.java,
                "delete %object%['s] guild"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuildOption(guildExpression)
            .peek { guild ->
                if (!SimpleEventHandler.handle(
                        GuildDeleteEvent(
                            FunnyEvent.EventCause.CONSOLE,
                            null,
                            guild
                        )
                    )
                ) {
                    return@peek
                }

                FunnyGuilds.getInstance().guildManager.deleteGuild(guild)
            }
    }

}