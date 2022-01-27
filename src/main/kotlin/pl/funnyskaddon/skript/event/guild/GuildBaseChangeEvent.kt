package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildBaseChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Base Change",
                GuildBaseChangeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildBaseChangeEvent::class.java,
                "guild base change"
            ).description("Wywoływany, kiedy zmieni się lokalizacja bazy gildii")
                .examples(
                    "on guild base change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%new base location%\""
                )
        }
    }

}