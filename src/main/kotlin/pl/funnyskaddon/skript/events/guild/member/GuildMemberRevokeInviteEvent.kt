package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberRevokeInviteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Revoke Invitation",
                GuildMemberRevokeInviteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberAcceptInviteEvent::class.java,
                "guild member revoke (invite|invitation)"
            )
        }
    }

}