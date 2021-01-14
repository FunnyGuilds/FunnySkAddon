package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildEnlargeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Enlarge",
                GuildEnlargeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildEnlargeEvent::class.java,
                "guild enlarge"
            );
        }
    }

}