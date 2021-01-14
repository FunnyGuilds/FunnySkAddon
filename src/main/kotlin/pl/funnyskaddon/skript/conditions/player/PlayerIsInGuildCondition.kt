package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildPlayerCondition
import pl.funnyskaddon.util.NegationUtil

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
            NegationUtil.negation(getGuild(event)?.equals(getUser(event)?.guild), isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}