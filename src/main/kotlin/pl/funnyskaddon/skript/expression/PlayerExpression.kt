package pl.funnyskaddon.skript.expression

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event

abstract class PlayerExpression<T>(val toString: String) : FunnyExpression<T>() {

    protected lateinit var playerExpression: Expression<OfflinePlayer>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        playerExpression = expression[0] as Expression<OfflinePlayer>
        return true
    }

    override fun toString(e: Event?, debug: Boolean): String {
        if (e != null) {
            return toString + " player \"" + playerExpression.toString(e, debug) + "\""
        }
        return "$toString player"
    }

}