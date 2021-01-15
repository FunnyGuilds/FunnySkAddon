package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Timespan
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import java.util.*

class GuildValidityTimeExpression : GuildExpression<Timespan>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildValidityTimeExpression::class.java,
                Timespan::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild validity time"
            )
        }
    }

    override fun get(event: Event): Array<Timespan>? {
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(Timespan(guild.validity - (Date().time)))
        }

        return null
    }

    override fun getReturnType(): Class<Timespan> {
        return Timespan::class.java
    }

}