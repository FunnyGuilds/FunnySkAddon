package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildAddDeputyEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddDeputyEffect::class.java,
                "add %offlineplayer% to %object%(|'s) guild deputies"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.addDeputy(User.get(getValue(event)))
    }

}