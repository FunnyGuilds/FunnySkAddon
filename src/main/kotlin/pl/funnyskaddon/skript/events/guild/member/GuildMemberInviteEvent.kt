package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberInviteEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Invite",
                GuildMemberInviteEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberInviteEvent::class.java,
                "member (invite|invitation)"
            );
        }
    }

}