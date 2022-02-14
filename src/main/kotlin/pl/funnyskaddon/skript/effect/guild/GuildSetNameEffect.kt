package pl.funnyskaddon.skript.effect.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildPreRenameEvent
import net.dzikoysk.funnyguilds.event.guild.GuildRenameEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effect.GuildValueEffect
import pl.funnyskaddon.skript.getGuild
import pl.funnyskaddon.skript.getValue


@FunnyDoc
@Name("Set Guild Name")
@Description("Ustawia (zmienia) nazwę gildii")
@Examples(
    "set \"AC4U\" guild name to \"FajnaNazwa\"",
)
class GuildSetNameEffect : GuildValueEffect<String>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetNameEffect::class.java,
                "set %guild%['s] name to %string%",
                "set %string%['s] [guild] name to %string%",
                "set %offlineplayer%['s] [guild] name to %string%",
                "set %location%['s] [guild] name to %string%",
                "set %block%['s] [guild] name to %string%",
                "set %object%['s] [guild] name to %string%"
            )
        }
    }

    override fun execute(event: Event) {
        event.getGuild(guildExpression)
            .peek { guild ->
                event.getValue(valueExpression)
                    .peek valuePeek@{ value ->
                        val oldName = guild.tag

                        val guildPreRenameEvent =
                            GuildPreRenameEvent(FunnyEvent.EventCause.CONSOLE, null, guild, oldName, value)
                        if (!SimpleEventHandler.handle(guildPreRenameEvent)) {
                            return@valuePeek
                        }

                        guild.name = guildPreRenameEvent.newName

                        SimpleEventHandler.handle(
                            GuildRenameEvent(
                                FunnyEvent.EventCause.CONSOLE,
                                null,
                                guild,
                                oldName,
                                value
                            )
                        )
                    }
            }
    }

}