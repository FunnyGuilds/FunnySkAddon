package pl.funnyskaddon.skript.condition

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean

abstract class GuildCondition : FunnyCondition() {

    lateinit var guildExpression: Expression<Any>

    override fun init(
        expression: Array<Expression<*>?>,
        matchedPattern: Int,
        isDelayed: Kleenean?,
        parseResult: SkriptParser.ParseResult?
    ): Boolean {
        guildExpression = expression[0] as Expression<Any>
        if (parseResult != null) {
            isNegated = ((matchedPattern >= 1) xor (parseResult.mark == 1))
        }
        return true
    }

}