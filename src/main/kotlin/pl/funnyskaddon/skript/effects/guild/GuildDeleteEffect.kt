package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildEffect

class GuildDeleteEffect : GuildEffect() {

    companion object {
        init {
            Skript.registerEffect(
                GuildDeleteEffect::class.java,
                "delete %offlineplayer% to %object%(|'s) "
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.delete()
    }

}