package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildExtendValidityEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Extend Validity",
                GuildExtendValidityEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildExtendValidityEvent::class.java,
                "guild extend validity"
            ).description("Wywoływany kiedy ważność gildii zostanie przedłużona")
                .examples(
                    "on guild extend validity:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%extend time%\""
                )
        }
    }

}