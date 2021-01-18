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
@Name("Set Guild Owner")
@Description("Ustawia właściciela gildii")
@Examples(
    "set \"AC4U\" guild owner to player",
)
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