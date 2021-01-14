package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.PlayerExpression

class PlayerKillsExpression : PlayerExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerKillsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%(|'s) [(rank|ranking)] kills"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        val user = getUser(event)
        var value = 0

        if (user != null && user.rank != null) {
            value = user.rank.kills
        }

        return arrayOf(value)
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}