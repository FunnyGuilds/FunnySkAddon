package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberLeaveEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Leave",
                GuildMemberLeaveEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaveEvent::class.java,
                "member leave"
            );
        }
    }

}