package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression

class GuildPositionExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildPositionExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [(rank|ranking)] position"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        val guild = getGuild(event)

        if(guild != null) {
            return arrayOf(guild.rank.position)
        }

        return null
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}