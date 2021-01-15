package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetHomeEffect : GuildValueEffect<Location>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetHomeEffect::class.java,
                "set %object%(|'s) guild (home|base) location to %location%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.home = getValue(event)
    }

}