package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetLocationEffect : GuildValueEffect<Location>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetLocationEffect::class.java,
                "set %object%(|'s) guild [endercrystal|core] location to %location%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.enderCrystal = getValue(event)
    }

}