package pl.funnyskaddon.skript.expression

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import org.bukkit.event.Event
import pl.funnyskaddon.skript.getGuild

abstract class GuildExpression<T>(val toString: String) : FunnyExpression<T>() {

    protected lateinit var guildExpression: Expression<*>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        guildExpression = expression[0]
        return true
    }

    override fun toString(e: Event?, debug: Boolean): String {
        if (e != null) {
            return toString + " guild \"" + e.getGuild(guildExpression).toString() + "\""
        }
        return "$toString guild"
    }

}