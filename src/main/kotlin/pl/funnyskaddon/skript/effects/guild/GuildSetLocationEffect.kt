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
@Description("Ustawie lokalizacje gildii (jej kryształu/serca)")
@Examples(
    "set \"FajnaNazwa\" guild core to (location at (100, 100, 100) in world \"world\")",
)
class GuildSetLocationEffect : GuildValueEffect<Location>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetLocationEffect::class.java,
                "set %object%(|'s) guild [endercrystal|core] location to %location%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.enderCrystal = getValue(event)
    }

}