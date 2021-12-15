package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import panda.std.Option
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Remove Guild Deputy")
@Description("Usuwa zastępce z gildii")
@Examples(
    "remove player from \"AC4U\" guild deputies",
)
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
        val userOption: Option<User> = FunnyGuilds.getInstance().userManager.findByPlayer(getValue(event))
        if (userOption.isEmpty) {
            return
        }
        val user = userOption.get()

        getGuild(event)?.removeDeputy(user)
    }

}