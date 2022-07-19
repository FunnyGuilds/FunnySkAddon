package pl.funnyskaddon.skript.event.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import net.dzikoysk.funnyguilds.event.rank.DeathsChangeEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class RankDeathsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Deaths Change",
                RankDeathsChangeEvent::class.java,
                DeathsChangeEvent::class.java,
                "[player] [(rank|ranking)] deaths change"
            ).description("Wywoływany, kiedy zmieni się liczba śmierci gracza w rankingu")
                .examples(
                    "on player deaths change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%affected player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%change%\""
                )
        }
    }

}