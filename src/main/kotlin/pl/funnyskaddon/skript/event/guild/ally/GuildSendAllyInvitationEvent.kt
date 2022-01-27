package pl.funnyskaddon.skript.event.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildSendAllyInvitationEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Send Ally Invitation",
                GuildSendAllyInvitationEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent::class.java,
                "guild send ally (invite|invitation)"
            ).description("Wywoływany, kiedy zostanie wysłane zaproszenie do sojuszu")
                .examples(
                    "on guild send ally invitation:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%ally%\""
                )
        }
    }

}