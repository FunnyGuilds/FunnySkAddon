package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.guild.Guild

abstract class ValueExpression<T> : FunnyExpression<Guild>() {

    protected lateinit var valueExpression: Expression<T>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        valueExpression = expression[0] as Expression<T>
        return true
    }

    override fun getReturnType(): Class<Guild>? {
        return Guild::class.java
    }

}