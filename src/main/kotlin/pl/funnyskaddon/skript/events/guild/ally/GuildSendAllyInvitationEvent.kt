package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildSendAllyInvitationEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Send Ally Invitation",
                GuildSendAllyInvitationEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent::class.java,
                "send ally (invite|invitation)"
            );
        }
    }

}