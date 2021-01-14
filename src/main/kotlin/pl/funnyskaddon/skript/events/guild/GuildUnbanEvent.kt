package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.events.guilds.CustomGuildCreateEvent

class GuildUnbanEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Unban",
                GuildUnbanEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildUnbanEvent::class.java,
                "guild Unban"
            );
        }
    }

}