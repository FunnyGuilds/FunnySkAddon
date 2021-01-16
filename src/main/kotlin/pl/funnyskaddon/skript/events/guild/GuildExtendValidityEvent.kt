package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildExtendValidityEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Extend Validity",
                GuildExtendValidityEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildExtendValidityEvent::class.java,
                "guild extend validity"
            )
        }
    }

}