package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import org.bukkit.OfflinePlayer

abstract class PlayerExpression<T> : FunnyExpression<T>() {

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

}