package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Date
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression

class GuildBornExpression : GuildExpression<Date>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildBornExpression::class.java,
                Date::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild born [date]"
            )
        }
    }

    override fun get(event: Event): Array<Date>? {
        val guild = getGuild(event)

        if(guild != null) {
            return arrayOf(Date(guild.born))
        }

        return null
    }

    override fun getReturnType(): Class<Date> {
        return Date::class.java
    }

}