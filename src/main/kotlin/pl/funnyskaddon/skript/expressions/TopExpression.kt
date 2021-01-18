package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean

abstract class TopExpression<T> : FunnyExpression<T>() {

    lateinit var position: Expression<Number>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        position = expression[0] as Expression<Number>
        return true
    }

}