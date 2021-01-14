package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

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