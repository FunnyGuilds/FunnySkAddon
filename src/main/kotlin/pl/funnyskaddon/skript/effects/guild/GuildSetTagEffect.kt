package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetTagEffect : GuildValueEffect<String>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetTagEffect::class.java,
                "set %object%(|'s) guild tag to %string%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.tag = getValue(event)
    }

}