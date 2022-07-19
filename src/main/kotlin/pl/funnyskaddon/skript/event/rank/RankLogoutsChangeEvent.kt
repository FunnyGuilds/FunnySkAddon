package pl.funnyskaddon.skript.event.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import net.dzikoysk.funnyguilds.event.rank.AssistsChangeEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class RankLogoutsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Logouts Change",
                RankLogoutsChangeEvent::class.java,
                AssistsChangeEvent::class.java,
                "[player] [(rank|ranking)] logouts change"
            ).description("Wywoływany, kiedy zmieni się liczba asyst gracza w rankingu")
                .examples(
                    "on player logouts change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%affected player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%change%\""
                )
        }
    }

}