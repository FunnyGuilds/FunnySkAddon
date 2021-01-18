package pl.funnyskaddon.skript.conditions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.util.NegationUtil

@FunnyDoc
@Name("Guild Is Banned")
@Description("Sprawdza czy gildia jest zbanowana")
@Examples(
    "if guild \"AC4U\" is banned:",
    "if guild \"AC4U\" is not banned:"
)
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