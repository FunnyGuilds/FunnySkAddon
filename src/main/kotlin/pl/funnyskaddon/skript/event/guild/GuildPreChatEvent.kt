package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildPreChatEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Pre Chat Event",
                GuildPreChatEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildPreChatEvent::class.java,
                "guild [pre( |-)]chat"
            )
                .description("Wywoływany, kiedy gracz wyśle wiadomość na chacie gildyjnym (przed otrzymaniem wiadomości przez inne gildie)")
                .examples(
                    "on guild pre chat:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%receivers%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%message%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%type%\""
                )
        }
    }

}