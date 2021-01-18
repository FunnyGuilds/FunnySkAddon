package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Date
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression

@FunnyDoc
@Name("Guild Validity Date")
@Description("Zwraca date do kiedy ważna jest gildia")
@Examples(
    "send \"%\"AC4U\" guild validity date%\"",
    "set {_time} to \"AC4U\" guild validity date"
)
class GuildValidityDateExpression : GuildExpression<Date>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildValidityDateExpression::class.java,
                Date::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild validity date"
            )
        }
    }

    override fun get(event: Event): Array<Date>? {
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(Date(guild.validity))
        }

        return null
    }

    override fun getReturnType(): Class<Date> {
        return Date::class.java
    }

}