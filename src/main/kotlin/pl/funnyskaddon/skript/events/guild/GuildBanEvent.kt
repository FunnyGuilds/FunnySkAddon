package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildBanEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Ban",
                GuildBanEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildBanEvent::class.java,
                "guild ban"
            )
        }
    }

}