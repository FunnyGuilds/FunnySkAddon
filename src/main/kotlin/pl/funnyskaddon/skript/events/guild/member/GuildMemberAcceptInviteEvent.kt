package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberAcceptInviteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Accept Invitation",
                GuildMemberAcceptInviteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberAcceptInviteEvent::class.java,
                "member accept (invite|invitation)"
            )
        }
    }

}