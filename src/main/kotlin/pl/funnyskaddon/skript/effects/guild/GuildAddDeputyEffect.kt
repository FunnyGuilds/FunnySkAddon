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
@Name("Add Guild Deputy")
@Description("Dodaje zastępce gildii")
@Examples(
    "add player to \"AC4U\" guild deputies",
)
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