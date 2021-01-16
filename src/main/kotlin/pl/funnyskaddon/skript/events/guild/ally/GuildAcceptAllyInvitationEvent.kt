package pl.funnyskaddon.skript.events.guild.ally

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildAcceptAllyInvitationEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Accept Ally Invitation",
                GuildAcceptAllyInvitationEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent::class.java,
                "guild accept ally (invite|invitation)"
            )
        }
    }

}