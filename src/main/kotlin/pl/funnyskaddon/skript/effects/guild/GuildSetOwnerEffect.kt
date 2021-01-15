package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildSetOwnerEffect : GuildValueEffect<OfflinePlayer>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetOwnerEffect::class.java,
                "set %object%(|'s) guild owner to %offlineplayer%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.owner = User.get(getValue(event))
    }

}