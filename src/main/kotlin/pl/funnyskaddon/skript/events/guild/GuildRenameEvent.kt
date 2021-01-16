package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildRenameEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Rename",
                GuildRenameEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildRenameEvent::class.java,
                "guild rename"
            )
        }
    }

}