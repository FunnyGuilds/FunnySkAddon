package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildEffect
import pl.funnyskaddon.skript.effects.GuildValueEffect

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