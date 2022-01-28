package pl.funnyskaddon.skript.condition.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.GuildPlayerCondition
import pl.funnyskaddon.skript.getGuild
import pl.funnyskaddon.skript.getPlayerOption
import pl.funnyskaddon.util.isPlayerInGuildRegion

@FunnyDoc
@Name("Is In Guild Region")
@Description("Sprawdza, czy gracz jest na terenie danej gildii")
@Examples(
    "if player is in guild \"AC4U\" region:",
    "if player is not in guild \"AC4U\" region:"
)
class PlayerIsInGuildRegionCondition : GuildPlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsInGuildRegionCondition::class.java, "(player |)%player% is in guild %object% region",
                "(player |)%player% is(n't| not) in guild %object% region"
            )
        }
    }

    override fun check(event: Event): Boolean {
        return event.getPlayerOption(playerExpression)
            .map { player -> event.getGuild(guildExpression)?.isPlayerInGuildRegion(player) ?: false }
            .orElseGet(false)
            .xor(isNegated)
    }

}