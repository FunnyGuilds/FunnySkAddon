package pl.funnyskaddon.skript.expression

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean

abstract class TopExpression<T> : FunnyExpression<T>() {

    protected lateinit var positionExpression: Expression<Number>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        positionExpression = expression[0] as Expression<Number>
        return true
    }

}