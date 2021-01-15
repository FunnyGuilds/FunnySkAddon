package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildRegionLeaveEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Region Leave",
                GuildRegionLeaveEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildBanEvent::class.java,
                "guild region leave"
            )
        }
    }

}