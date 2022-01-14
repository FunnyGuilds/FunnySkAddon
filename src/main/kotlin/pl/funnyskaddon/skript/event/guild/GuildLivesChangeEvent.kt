package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildLivesChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Lives Change",
                GuildLivesChangeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent::class.java,
                "guild lives change"
            ).description("Wywoływany kiedy zmieni się ilość żyć gildii")
                .examples(
                    "on guild lives change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%lives%\""
                )
        }
    }

}