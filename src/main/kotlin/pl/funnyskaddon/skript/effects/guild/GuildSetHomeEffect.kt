package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Set Guild Home")
@Description("Ustawie (zmienia) lokalizacje bazy gildii")
@Examples(
    "set \"FajnaNazwa\" guild home to (location at (100, 100, 100) in world \"world\")",
)
class GuildSetHomeEffect : GuildValueEffect<Location>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetHomeEffect::class.java,
                "set %object%(|'s) guild (home|base) location to %location%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.home = getValue(event)
    }

}