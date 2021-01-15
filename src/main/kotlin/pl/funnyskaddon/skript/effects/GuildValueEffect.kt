package pl.funnyskaddon.skript.effects

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import org.bukkit.event.Event

abstract class GuildValueEffect<T>(private var inverted: Boolean) : GuildEffect() {

    lateinit var value: Expression<T>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        if(inverted || matchedPattern > 1) {
            value = expression[0] as Expression<T>
            guild = expression[1] as Expression<Any>
        } else {
            value = expression[1] as Expression<T>
            guild = expression[0] as Expression<Any>
        }
        return true
    }

    fun getValue(event: Event?): T? {
        return value.getSingle(event)
    }

}