package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberLeaderEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Leader",
                GuildMemberLeaderEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaderEvent::class.java,
                "member leader"
            );
        }
    }

}