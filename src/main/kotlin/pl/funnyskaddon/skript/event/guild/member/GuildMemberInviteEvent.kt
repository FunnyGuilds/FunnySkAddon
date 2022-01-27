package pl.funnyskaddon.skript.event.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberInviteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Invite",
                GuildMemberInviteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberInviteEvent::class.java,
                "guild member (invite|invitation)"
            ).description("Wywoływany, kiedy gracz zostanie zaproszony do gildii")
                .examples(
                    "on guild member invite:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%member%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}