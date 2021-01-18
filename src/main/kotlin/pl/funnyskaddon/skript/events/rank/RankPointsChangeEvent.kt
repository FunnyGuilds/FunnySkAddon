package pl.funnyskaddon.skript.events.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import net.dzikoysk.funnyguilds.event.rank.PointsChangeEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class RankPointsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Points Change",
                RankPointsChangeEvent::class.java,
                PointsChangeEvent::class.java,
                "[player] [rank|ranking] points change"
            ).description("Wywoływany kiedy zmieni się ilość punktów gracza w rankingu")
                .examples(
                    "on player points change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%change%\""
                )
        }
    }

}