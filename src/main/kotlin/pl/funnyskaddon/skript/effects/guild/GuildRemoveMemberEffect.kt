package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Remove Guild Member")
@Description("Usuwa członka z gildii")
@Examples(
    "remove player from \"AC4U\" guild members",
)
class GuildRemoveMemberEffect : GuildValueEffect<OfflinePlayer>(true) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveMemberEffect::class.java,
                "remove %offlineplayer% from %object%(|'s) guild members"
            )
        }
    }

    override fun execute(event: Event?) {
        val user = User.get(getValue(event))
        val guild = getGuild(event)

        guild?.removeMember(user)
        user?.guild = null
    }

}