package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

class GuildDoesExistCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildDoesExistCondition::class.java, "guild %string% (does|) exist(s|)",
                "guild %string% (doesn't|does not) exist(s|)"
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