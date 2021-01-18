package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Timespan
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression
import java.util.*

@FunnyDoc
@Name("Guild Validity Time")
@Description("Zwraca czas przez jaki gildia jeszcze jest ważna")
@Examples(
    "send \"%\"AC4U\" guild validity time%\"",
    "set {_time} to \"AC4U\" guild validity time"
)
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