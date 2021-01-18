package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildDeleteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Delete",
                GuildDeleteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildDeleteEvent::class.java,
                "guild (delete|deletion)"
            ).description("Wywoływany kiedy gildia zostanie usunięta")
                .examples(
                    "on guild delete:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}