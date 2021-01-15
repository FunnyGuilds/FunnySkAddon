package pl.funnyskaddon.skript.events.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent

class GuildLivesChangeEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Lives Change",
                GuildLivesChangeEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent::class.java,
                "guild lives change"
            )
        }
    }

}