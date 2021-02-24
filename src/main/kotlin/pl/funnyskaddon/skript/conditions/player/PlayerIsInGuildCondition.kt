package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.GuildPlayerCondition

@FunnyDoc
@Name("Is In Guild")
@Description("Sprawdza czy gracz jest członkiem danej gildii")
@Examples(
    "if player is in guild \"AC4U\":",
    "if player is not in guild \"AC4U\":"
)
class PlayerIsInGuildCondition : GuildPlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsInGuildCondition::class.java, "(player |)%offlineplayer% is in guild %object%",
                "(player |)%offlineplayer% is(n't| not) in guild %object%"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            (getGuild(event)?.equals(getUser(event)?.guild))?.xor(isNegated)!!
        } catch (ex: Exception) {
            !isNegated
        }
    }

}