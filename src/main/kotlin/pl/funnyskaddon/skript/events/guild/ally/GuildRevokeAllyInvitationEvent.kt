package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildRevokeAllyInvitationEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Revoke Ally Invitation",
                GuildRevokeAllyInvitationEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent::class.java,
                "revoke ally (invite|invitation)"
            );
        }
    }

}