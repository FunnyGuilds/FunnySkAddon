package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildEffect

@FunnyDoc
@Name("Delete Guild")
@Description("Usuwa gildie")
@Examples(
    "delete \"AC4U\" guild",
)
class GuildDeleteEffect : GuildEffect() {

    companion object {
        init {
            Skript.registerEffect(
                GuildDeleteEffect::class.java,
                "delete %object%(|'s) guild"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.delete()
    }

}