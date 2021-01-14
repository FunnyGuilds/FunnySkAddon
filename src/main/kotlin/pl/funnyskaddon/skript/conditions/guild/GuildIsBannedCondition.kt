package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

class GuildIsBannedCondition : GuildCondition() {

    companion object {
        init {
            Skript.registerCondition(
                GuildIsBannedCondition::class.java, "guild %object% is banned",
                "guild %object% is(n't| not) banned"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(getGuild(event)?.isBanned, isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}