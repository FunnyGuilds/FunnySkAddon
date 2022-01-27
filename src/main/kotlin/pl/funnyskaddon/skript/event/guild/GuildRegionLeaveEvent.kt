package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildRegionLeaveEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Region Leave",
                GuildRegionLeaveEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildBanEvent::class.java,
                "guild region leave"
            ).description("Wywoływany, kiedy gracz opuści teren gildii")
                .examples(
                    "on guild region leave:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                )
        }
    }

}