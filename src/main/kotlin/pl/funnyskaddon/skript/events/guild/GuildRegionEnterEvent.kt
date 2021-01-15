package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildRegionEnterEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Region Enter",
                GuildRegionEnterEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildRegionEnterEvent::class.java,
                "guild region enter"
            )
        }
    }

}