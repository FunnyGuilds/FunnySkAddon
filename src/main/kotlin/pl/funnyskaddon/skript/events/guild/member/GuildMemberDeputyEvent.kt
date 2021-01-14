package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildMemberDeputyEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Member Deputy",
                GuildMemberDeputyEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberDeputyEvent::class.java,
                "member deputy"
            )
        }
    }

}