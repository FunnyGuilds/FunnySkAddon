package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Set Guild Tag")
@Description("Ustawie (zmienia) tag gildii")
@Examples(
    "set \"FajnaNazwa\" guild tag to \"AC4U\"",
)
class GuildSetTagEffect : GuildValueEffect<String>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetTagEffect::class.java,
                "set %object%(|'s) guild tag to %string%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.tag = getValue(event)
    }

}