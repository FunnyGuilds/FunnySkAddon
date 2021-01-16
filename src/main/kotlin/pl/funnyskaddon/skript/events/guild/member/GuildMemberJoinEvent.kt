package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberJoinEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Join",
                GuildMemberJoinEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent::class.java,
                "guild member join"
            )
        }
    }

}