package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event

abstract class PlayerExpression<T> : SimpleExpression<T>() {

    var player: Expression<OfflinePlayer>? = null

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        player = expression[0] as Expression<OfflinePlayer>
        return true
    }

    fun getUser(event: Event?): User? {
        return try {
            User.get(player?.getSingle(event))
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