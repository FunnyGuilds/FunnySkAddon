package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Set Guild Lives")
@Description("Ustawie ilość żyć gildii")
@Examples(
    "set amount \"FajnaNazwa\" guild lives to 6",
)
class GuildSetLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildSetLivesEffect::class.java,
                "set [(number|amount) of] %object%(|'s) guild lives to %number%"
            )
        }
    }

    override fun execute(event: Event?) {
        getGuild(event)?.lives = getValue(event)?.toInt()!!
    }

}