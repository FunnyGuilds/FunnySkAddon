package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMoveEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Move",
                GuildMoveEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildMoveEvent::class.java,
                "guild move"
            ).description("Wywoływany, kiedy zmieni się lokalizacja serca gildii")
                .examples(
                    "on guild move:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%new location%\""
                )
        }
    }

}