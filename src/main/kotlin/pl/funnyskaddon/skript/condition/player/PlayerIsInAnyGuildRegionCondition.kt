package pl.funnyskaddon.skript.condition.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.PlayerCondition
import pl.funnyskaddon.skript.getPlayerOption

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
                "(player |)%player% is(n't| not) in any guild region"
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getPlayerOption(playerExpression)
            .map { player -> FunnyGuilds.getInstance().regionManager.isInRegion(player.location) }
            .orElseGet(false).xor(isNegated)
    }

}