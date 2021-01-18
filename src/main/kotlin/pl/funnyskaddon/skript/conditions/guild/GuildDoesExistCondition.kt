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
@Name("Guild Does Exist")
@Description("Sprawdza czy gildia istnieje")
@Examples(
    "if guild \"AC4U\" exists:",
    "if guild \"AC4U\" doesn't exists:"
)
class GuildDoesExistCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildDoesExistCondition::class.java, "guild %object% (does|) exist(s|)",
                "guild %object% (doesn't|does not) exist(s|)"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(getGuild(event) != null, isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}