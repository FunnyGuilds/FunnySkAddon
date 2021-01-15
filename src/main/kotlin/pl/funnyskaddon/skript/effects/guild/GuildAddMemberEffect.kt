package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.effects.GuildValueEffect

class GuildAddMemberEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddMemberEffect::class.java,
                "add %offlineplayer% to %object%(|'s) guild members"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.addMember(User.get(getValue(event)))
    }

}