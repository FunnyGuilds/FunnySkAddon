package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildEntityExplodeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Entity Explode",
                GuildEntityExplodeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildEntityExplodeEvent::class.java,
                "guild entity explode"
            )
        }
    }

}