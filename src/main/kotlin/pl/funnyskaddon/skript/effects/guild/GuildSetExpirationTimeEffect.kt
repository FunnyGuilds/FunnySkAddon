package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.util.Date
import ch.njol.skript.util.Timespan
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetExpirationTimeEffect : GuildValueEffect<Date>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetExpirationTimeEffect::class.java,
                "set (expiration|validity) [date] of %object%(|'s) guild to %date%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.validity = getValue(event)?.timestamp!!
    }

}