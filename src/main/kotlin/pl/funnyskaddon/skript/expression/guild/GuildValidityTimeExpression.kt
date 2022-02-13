package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Timespan
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild
import java.util.*

@FunnyDoc
@Name("Guild Validity Time")
@Description("Zwraca czas przez jaki gildia jeszcze jest ważna")
@Examples(
    "send \"%\"AC4U\" guild validity time%\"",
    "set {_time} to \"AC4U\" guild validity time"
)
class GuildValidityTimeExpression : GuildExpression<Timespan>("validity time of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildValidityTimeExpression::class.java,
                Timespan::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] validity time",
                "%string%['s] validity time",
                "%offlineplayer%['s] guild['s] validity time",
                "%location%['s] guild['s] validity time",
                "%block%['s] guild['s] validity time",
                "%object%['s] guild['s] validity time",
            )
        }
    }

    override fun get(event: Event): Array<Timespan>? {
        return event.getGuild(guildExpression)
            .map { guild -> Timespan(guild.validity - (Date().time)) }
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Timespan> {
        return Timespan::class.java
    }

}