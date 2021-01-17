package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetSizeEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetSizeEffect::class.java,
                "set size of %object%(|'s) guild to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.region?.size = getValue(event)?.toInt()!!
    }

}