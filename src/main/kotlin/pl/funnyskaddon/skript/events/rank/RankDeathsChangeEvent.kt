package pl.funnyskaddon.skript.events.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent

class RankDeathsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Deaths Change",
                RankDeathsChangeEvent::class.java,
                DeathsChangeEvent::class.java,
                "deaths change"
            )
        }
    }

}