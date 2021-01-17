package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildAddLiveEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddLiveEffect::class.java,
                "add %number% live[s] to %object%(|'s) guild"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)

        var change = 0
        val value = getValue(event)
        if (value != null) {
            change = value.toInt()
        }

        guild?.lives = guild?.lives?.plus(change)!!
    }

}