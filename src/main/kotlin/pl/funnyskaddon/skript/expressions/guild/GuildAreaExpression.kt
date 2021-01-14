package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression
import kotlin.math.abs

class GuildAreaExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAreaExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [region] area"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        val guild = getGuild(event)

        if(guild != null) {
            val region = guild.region
            val area: Int = abs((region.lowerX - region.upperX) * (region.lowerZ - region.upperZ))

            return arrayOf(area)
        }

        return null
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}