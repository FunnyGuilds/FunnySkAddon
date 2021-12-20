package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.util.getGuild

abstract class GuildExpression<T> : FunnyExpression<T>() {

    protected lateinit var guildExpression: Expression<Any>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        guildExpression = expression[0] as Expression<Any>
        return true
    }

    fun getGuild(event: Event?): Guild? {
        return try {
            guildExpression.getSingle(event)?.getGuild()
        } catch (ex: Exception) {
            null
        }
    }
}