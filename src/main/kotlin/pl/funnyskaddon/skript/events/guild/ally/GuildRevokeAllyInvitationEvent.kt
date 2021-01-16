package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildRevokeAllyInvitationEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Revoke Ally Invitation",
                GuildRevokeAllyInvitationEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent::class.java,
                "guild revoke ally (invite|invitation)"
            )
        }
    }

}