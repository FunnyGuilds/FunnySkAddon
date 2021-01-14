package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildAcceptAllyInvitationEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Accept Ally Invitation",
                GuildAcceptAllyInvitationEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent::class.java,
                "accept ally (invite|invitation)"
            );
        }
    }

}