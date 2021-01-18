package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildBreakAllyEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Break Ally",
                GuildBreakAllyEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent::class.java,
                "guild break ally"
            ).description("Wywoływany kiedy gildia rozwiąże sojusz")
                .examples(
                    "on guild break ally:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%ally%\""
                )
        }
    }

}