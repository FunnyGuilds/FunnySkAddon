package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberLeaderEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Leader",
                GuildMemberLeaderEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaderEvent::class.java,
                "guild member (leader|owner)",
                "guild change (leader|owner)"
            ).description("Wywoływany kiedy zostanie wybrany nowy właściciel gildii")
                .examples(
                    "on guild member owner:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%member%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}