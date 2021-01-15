package pl.funnyskaddon.skript.effects.player

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.PlayerEffect
import pl.funnyskaddon.util.GuildUtil

class PlayerSetGuildEffect : PlayerEffect<Any>(false) {

    companion object {
        init {
            Skript.registerEffect(PlayerSetGuildEffect::class.java, "guild set %offlineplayer%(|'s) guild to %object%")
        }
    }

    override fun execute(event: Event?) {
        getUser(event)?.guild = getGuild(event)
    }

    fun getGuild(event: Event?): Guild? {
        return try {
            GuildUtil.getGuild(getValue(event))
        } catch (ex: Exception) {
            null
        }
    }

}