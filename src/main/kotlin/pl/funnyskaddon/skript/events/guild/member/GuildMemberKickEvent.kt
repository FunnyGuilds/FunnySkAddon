package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberKickEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Kick",
                GuildMemberKickEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberKickEvent::class.java,
                "member kick"
            )
        }
    }

}