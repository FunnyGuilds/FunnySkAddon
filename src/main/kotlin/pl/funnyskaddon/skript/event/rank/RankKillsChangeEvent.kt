package pl.funnyskaddon.skript.event.rank

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import net.dzikoysk.funnyguilds.event.rank.KillsChangeEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class RankKillsChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Kills Change",
                RankKillsChangeEvent::class.java,
                KillsChangeEvent::class.java,
                "[player] [(rank|ranking)] kills change"
            ).description("Wywoływany, kiedy zmieni się liczba zabójstw gracza w rankingu")
                .examples(
                    "on player kills change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%affected player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%change%\""
                )
        }
    }

}