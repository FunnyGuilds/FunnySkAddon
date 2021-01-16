package pl.funnyskaddon.skript.events.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberLeaveEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Leave",
                GuildMemberLeaveEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberLeaveEvent::class.java,
                "guild member leave"
            )
        }
    }

}