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
@Name("Guild Born")
@Description("Zwraca date narodzin gildii")
@Examples(
    "send \"%\"\"AC4U\"\" guild born date%\"",
    "set {_born} to \"AC4U\" guild born date"
)
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

        if (guild != null) {
            return arrayOf(Date(guild.born))
        }

        return null
    }

    override fun getReturnType(): Class<Date> {
        return Date::class.java
    }

}