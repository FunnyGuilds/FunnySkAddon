package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.NegationUtil
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.skript.expressions.player.PlayerDeathsExpression
import pl.funnyskaddon.util.GuildUtil
import pl.funnyskaddon.util.GuildUtil.Companion.getGuild

class GuildCanBuildCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildCanBuildCondition::class.java, "guild %object% can build",
                "guild %object% (cannot|can't) build"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(getGuild(event)?.canBuild(), isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}