package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetLivesEffect::class.java,
                "set [(number|amount) of] %object%(|'s) guild lives to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.lives = getValue(event)?.toInt()!!
    }

}