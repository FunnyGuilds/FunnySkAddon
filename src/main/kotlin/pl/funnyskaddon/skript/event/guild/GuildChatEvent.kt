package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildChatEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Chat Event",
                GuildChatEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildChatEvent::class.java,
                "guild chat"
            ).description("Wywoływany, kiedy gracz wyśle wiadomość na chacie gildyjnym")
                .examples(
                    "on guild chat:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%receivers%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%message%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%type%\""
                )
        }
    }

}