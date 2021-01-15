package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildRemoveDeputyEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveDeputyEffect::class.java,
                "remove %offlineplayer% from %object%(|'s) guild deputies"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.removeDeputy(User.get(getValue(event)))
    }

}