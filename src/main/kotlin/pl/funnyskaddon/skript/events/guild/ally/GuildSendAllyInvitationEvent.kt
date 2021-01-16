package pl.funnyskaddon.skript.events.guild.ally

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
            )
        }
    }

}