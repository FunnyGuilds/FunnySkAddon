package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildBanEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Ban",
                GuildBanEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildBanEvent::class.java,
                "guild ban"
            ).description("Wywoływany kiedy gildia zostanie zbanowana")
                .examples(
                    "on guild ban:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%reason%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%time%\""
                )
        }
    }

}