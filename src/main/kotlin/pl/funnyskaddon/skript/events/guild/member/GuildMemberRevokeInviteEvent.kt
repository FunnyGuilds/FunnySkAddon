package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberRevokeInviteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Revoke Invitation",
                GuildMemberRevokeInviteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberAcceptInviteEvent::class.java,
                "member revoke (invite|invitation)"
            )
        }
    }

}