package pl.funnyskaddon.skript.event.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberAcceptInviteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Accept Invitation",
                GuildMemberAcceptInviteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberAcceptInviteEvent::class.java,
                "guild member accept (invite|invitation)"
            ).description("Wywoływany, kiedy gracz zaakceptuje zaproszenie do gildii")
                .examples(
                    "on guild member accept invitation:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%member%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}