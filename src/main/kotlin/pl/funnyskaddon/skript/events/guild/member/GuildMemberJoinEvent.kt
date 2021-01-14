package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberJoinEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Join",
                GuildMemberJoinEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent::class.java,
                "member join"
            )
        }
    }

}