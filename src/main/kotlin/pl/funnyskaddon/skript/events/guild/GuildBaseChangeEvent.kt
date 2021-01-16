package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildBaseChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Base Change",
                GuildBaseChangeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildBaseChangeEvent::class.java,
                "guild base change"
            )
        }
    }

}