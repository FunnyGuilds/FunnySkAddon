package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildCreateEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Create",
                GuildCreateEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildCreateEvent::class.java,
                "guild (create|creation)"
            ).description("Wywoływany kiedy gildia zostanie utworzona")
                .examples(
                    "on guild create:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                )
        }
    }

}