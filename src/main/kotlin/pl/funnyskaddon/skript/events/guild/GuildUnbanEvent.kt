package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildUnbanEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Unban",
                GuildUnbanEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildUnbanEvent::class.java,
                "guild Unban"
            ).description("Wywoływany kiedy gildia zostanie odbanowana")
                .examples(
                    "on guild unban:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}