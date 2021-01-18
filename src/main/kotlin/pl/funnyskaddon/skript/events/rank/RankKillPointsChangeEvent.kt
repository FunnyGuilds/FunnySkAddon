package pl.funnyskaddon.skript.events.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.events.rank.CustomKillPointsChangeEvent

@FunnyDoc
class RankKillPointsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Kill Points Change",
                RankKillPointsChangeEvent::class.java,
                CustomKillPointsChangeEvent::class.java,
                "[player] kill [rank|ranking] points change"
            ).description("Wywoływany kiedy zmieni się ilość punktów gracza w rankingu w wyniku jego zabójstwa")
                .examples(
                    "on kill points change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%attacker%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%victim%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%change%\""
                )
        }
    }

}