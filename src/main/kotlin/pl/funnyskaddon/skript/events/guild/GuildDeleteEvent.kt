package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.events.guilds.CustomGuildCreateEvent

class GuildDeleteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Delete",
                GuildDeleteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildDeleteEvent::class.java,
                "guild (delete|deletion)"
            );
        }
    }

}