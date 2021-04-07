package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.event.FunnyEvent
import net.dzikoysk.funnyguilds.event.SimpleEventHandler
import net.dzikoysk.funnyguilds.event.guild.GuildPreTagChangeEvent
import net.dzikoysk.funnyguilds.event.guild.GuildTagChangeEvent
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Set Guild Tag")
@Description("Ustawie (zmienia) tag gildii")
@Examples(
    "set \"FajnaNazwa\" guild tag to \"AC4U\"",
)
class GuildSetTagEffect : GuildValueEffect<String>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetTagEffect::class.java,
                "set %object%(|'s) guild tag to %string%"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)
        val value = getValue(event)

        val oldTag = guild?.tag

        if (!SimpleEventHandler.handle(
                GuildPreTagChangeEvent(
                    FunnyEvent.EventCause.CONSOLE,
                    null,
                    guild,
                    oldTag,
                    value
                )
            )
        ) {
            return
        }

        guild?.tag = value

        SimpleEventHandler.handle(GuildTagChangeEvent(FunnyEvent.EventCause.CONSOLE, null, guild, oldTag, value))
    }

}