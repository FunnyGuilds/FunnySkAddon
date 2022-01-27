package pl.funnyskaddon.skript.event.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberJoinEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Join",
                GuildMemberJoinEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent::class.java,
                "guild member join"
            ).description("Wywoływany, kiedy gracz dołączy do gildii")
                .examples(
                    "on guild member join:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%member%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}