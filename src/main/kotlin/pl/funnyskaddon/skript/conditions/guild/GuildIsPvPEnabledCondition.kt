package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

class GuildIsPvPEnabledCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildIsPvPEnabledCondition::class.java, "guild %object% pvp is (enabled|on)",
                "guild %object% pvp is (disabled|off)"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(getGuild(event)?.pvP, isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}