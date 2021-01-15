package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetNameEffect : GuildValueEffect<String>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetNameEffect::class.java,
                "set %object%(|'s) guild name to %string%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.name = getValue(event)
    }

}