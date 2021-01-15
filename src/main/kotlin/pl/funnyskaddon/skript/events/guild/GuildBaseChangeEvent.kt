package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

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