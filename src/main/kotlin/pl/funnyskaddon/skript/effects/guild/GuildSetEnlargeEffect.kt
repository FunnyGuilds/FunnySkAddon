package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetEnlargeEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetEnlargeEffect::class.java,
                "set enlarge [level|lvl] of %object%(|'s) guild to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.region?.enlarge = getValue(event)?.toInt()!!
    }

}