package pl.funnyskaddon.skript.event.guild.member

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildMemberKickEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Member Kick",
                GuildMemberKickEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.member.GuildMemberKickEvent::class.java,
                "guild member kick"
            ).description("Wywoływany kiedy gracz zostanie wyrzucony z gildii")
                .examples(
                    "on guild kick:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%member%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\""
                )
        }
    }

}