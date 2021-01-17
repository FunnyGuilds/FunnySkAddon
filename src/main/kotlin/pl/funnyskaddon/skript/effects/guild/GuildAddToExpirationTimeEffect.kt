package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.util.Timespan
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildAddToExpirationTimeEffect : GuildValueEffect<Timespan>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddToExpirationTimeEffect::class.java,
                "add %timespan% to %object%(|'s) guild (expiration|validity) [time]"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)

        var change = 0L
        val value = getValue(event)
        if (value != null) {
            change = value.milliSeconds
        }

        guild?.validity = guild?.validity?.plus(change)!!
    }

}