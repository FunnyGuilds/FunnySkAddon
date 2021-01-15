package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.util.GuildUtil

abstract class GuildExpression<T> : SimpleExpression<T>() {

    lateinit var guild: Expression<Any>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        guild = expression[0] as Expression<Any>
        return true
    }

    fun getGuild(event: Event?): Guild? {
        return try {
            GuildUtil.getGuild(guild.getSingle(event))
        } catch (ex: Exception) {
            null
        }
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

}