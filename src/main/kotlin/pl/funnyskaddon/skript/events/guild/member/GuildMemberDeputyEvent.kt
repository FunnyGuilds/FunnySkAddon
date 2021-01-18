package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberDeputyEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Deputy",
                GuildMemberDeputyEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberDeputyEvent::class.java,
                "guild member deputy"
            ).description("Wywoływany kiedy zostanie wybrany nowy zastępca gildii")
                .examples(
                    "on guild member deputy:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%member%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}