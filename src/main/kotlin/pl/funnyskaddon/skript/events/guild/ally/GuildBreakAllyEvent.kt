package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildBreakAllyEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Break Ally",
                GuildBreakAllyEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent::class.java,
                "break ally)"
            );
        }
    }

}