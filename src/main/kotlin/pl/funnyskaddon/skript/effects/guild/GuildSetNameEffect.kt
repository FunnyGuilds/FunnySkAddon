package pl.funnyskaddon.skript.effects.guild

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
import pl.funnyskaddon.skript.effects.GuildValueEffect


@FunnyDoc
@Name("Set Guild Name")
@Description("Ustawia (zmienia) nazwe gildii")
@Examples(
    "set \"AC4U\" guild name to \"FajnaNazwa\"",
)
class GuildSetNameEffect : GuildValueEffect<String>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetNameEffect::class.java,
                "set %object%(|'s) guild name to %string%"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)
        val value = getValue(event)

        val oldName = guild?.name

        if (!SimpleEventHandler.handle(
                GuildPreRenameEvent(
                    FunnyEvent.EventCause.CONSOLE,
                    null,
                    guild,
                    oldName,
                    value
                )
            )
        ) {
            return
        }

        guild?.name = value

        SimpleEventHandler.handle(GuildRenameEvent(FunnyEvent.EventCause.CONSOLE, null, guild, oldName, value))
    }

}