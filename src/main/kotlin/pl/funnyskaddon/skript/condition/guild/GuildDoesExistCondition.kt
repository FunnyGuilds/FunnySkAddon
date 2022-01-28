package pl.funnyskaddon.skript.condition.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.GuildCondition
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Does Exist")
@Description("Sprawdza, czy gildia istnieje")
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

    override fun check(event: Event): Boolean {
        return event.getGuildOption(guildExpression)
            .isPresent
            .xor(isNegated)
    }

}