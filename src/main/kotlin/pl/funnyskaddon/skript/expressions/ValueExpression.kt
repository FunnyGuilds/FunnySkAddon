package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event

abstract class ValueExpression<T> : FunnyExpression<Guild>() {

    lateinit var value: Expression<T>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        value = expression[0] as Expression<T>
        return true
    }

    fun getValue(event: Event?): T? {
        return value.getSingle(event)
    }

    override fun getReturnType(): Class<Guild>? {
        return Guild::class.java
    }

}