package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildTagChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Tag Change",
                GuildTagChangeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildPreTagChangeEvent::class.java,
                "guild tag change"
            ).description("Wywoływany kiedy zmieni się tag gildii")
                .examples(
                    "on guild tag change:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%old tag%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%old name%\""
                )
        }
    }

}