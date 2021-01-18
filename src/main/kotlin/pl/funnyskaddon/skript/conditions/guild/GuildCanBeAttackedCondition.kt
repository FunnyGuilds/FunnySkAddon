package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

@FunnyDoc
@Name("Guild Can Be Attacked")
@Description("Sprawdza czy gildia może być zaatakowana")
@Examples(
    "if guild \"AC4U\" can be attacked:",
    "if guild \"AC4U\" can't be attacked:"
)
class GuildCanBeAttackedCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildCanBeAttackedCondition::class.java, "guild %object% can be attacked",
                "guild %object% (cannot|can't) be attacked"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(getGuild(event)?.canBeAttacked(), isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}