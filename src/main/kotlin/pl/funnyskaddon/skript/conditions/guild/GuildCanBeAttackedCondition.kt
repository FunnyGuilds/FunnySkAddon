package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

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