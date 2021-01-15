package pl.funnyskaddon.skript.effects

import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.util.GuildUtil

abstract class ValueGuildEffect<T> : GuildEffect() {

    var value: Expression<T>? = null

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        value = expression[1] as Expression<T>
        return true
    }

    fun getValue(event: Event?): T? {
        return value?.getSingle(event)
    }

}