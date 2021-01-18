package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildEnlargeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Enlarge",
                GuildEnlargeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildEnlargeEvent::class.java,
                "guild enlarge"
            ).description("Wywoływany kiedy zmieni się poziom powiększenia gildii")
                .examples(
                    "on guild enlarge:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}