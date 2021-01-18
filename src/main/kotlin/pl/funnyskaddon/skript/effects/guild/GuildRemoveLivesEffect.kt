package pl.funnyskaddon.skript.effects.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.effects.GuildValueEffect

@FunnyDoc
@Name("Remove Guild Lives")
@Description("Usuwa życia z gildii")
@Examples(
    "remove 4 lives from \"AC4U\" guild",
)
class GuildRemoveLivesEffect : GuildValueEffect<Number>(false) {

    companion object {
        init {
            Skript.registerEffect(
                GuildRemoveLivesEffect::class.java,
                "remove %number% live[s] from %object%(|'s) guild"
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

        guild?.lives = guild?.lives?.minus(change)!!
    }

}