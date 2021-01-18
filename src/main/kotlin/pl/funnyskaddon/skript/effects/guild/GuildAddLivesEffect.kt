package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Add Guild Lives")
@Description("Dodaje życia do gildii")
@Examples(
    "add 4 lives to \"AC4U\" guild",
)
class GuildAddLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildAddLivesEffect::class.java,
                "add %number% live[s] to %object%(|'s) guild"
            )
        }
    }

    override fun execute(event: Event?) {
        val guild = getGuild(event)

        var change = 0
        val value = getValue(event)
        if (value != null) {
            change = value.toInt()
        }

        guild?.lives = guild?.lives?.plus(change)!!
    }

}