package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.PlayerCondition
import pl.funnyskaddon.util.isInGuildRegion

@FunnyDoc
@Name("Is In Any Guild Region")
@Description("Sprawdza czy gracz jest na terenie jakiejkolwiek gildii")
@Examples(
    "if player is in any guild region:",
    "if player is not in any guild region:"
)
class PlayerIsInAnyGuildRegionCondition : PlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsInAnyGuildRegionCondition::class.java, "(player |)%player% is in any guild region",
                "(player |)%player% is(n't| not) in guild any region"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            getPlayer(event)?.isInGuildRegion()?.xor(isNegated)!!
        } catch (ex: Exception) {
            !isNegated
        }
    }

}