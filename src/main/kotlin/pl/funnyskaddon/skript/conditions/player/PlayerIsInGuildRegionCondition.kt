package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildPlayerCondition
import pl.funnyskaddon.util.NegationUtil
import pl.funnyskaddon.util.isPlayerInGuildRegion

class PlayerIsInGuildRegionCondition : GuildPlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsInGuildRegionCondition::class.java, "(player |)%player% is in guild region %object%",
                "(player |)%player% is(n't| not) in guild region %object%"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(getGuild(event)?.isPlayerInGuildRegion(getPlayer(event)), isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}