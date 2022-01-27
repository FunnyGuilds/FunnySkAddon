package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildRegionEnterEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Region Enter",
                GuildRegionEnterEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildRegionEnterEvent::class.java,
                "guild region enter"
            ).description("Wywoływany, kiedy gracz wejdzie na teren gildii")
                .examples(
                    "on gild region enter:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                )
        }
    }

}