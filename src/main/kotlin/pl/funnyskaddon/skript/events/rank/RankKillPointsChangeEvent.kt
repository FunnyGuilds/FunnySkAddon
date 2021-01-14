package pl.funnyskaddon.skript.events.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.events.rank.CustomKillPointsChangeEvent

class RankKillPointsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Kill Points Change",
                RankKillPointsChangeEvent::class.java,
                CustomKillPointsChangeEvent::class.java,
                "kill points change"
            )
        }
    }

}