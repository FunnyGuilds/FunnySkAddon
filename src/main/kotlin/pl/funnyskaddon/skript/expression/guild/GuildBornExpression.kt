package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Date
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild
import java.time.Instant

@FunnyDoc
@Name("Guild Born")
@Description("Zwraca date narodzin gildii")
@Examples(
    "send \"%\"\"AC4U\"\" guild born date%\"",
    "set {_born} to \"AC4U\" guild born date"
)
class GuildBornExpression : GuildExpression<Date>("born data of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildBornExpression::class.java,
                Date::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] born [date]",
                "%string%['s] [guild] born [date]",
                "%offlineplayer%['s] guild['s] born [date]",
                "%location%['s] guild['s] born [date]",
                "%block%['s] guild['s] born [date]",
                "%object%['s] guild['s] born [date]"
            )
        }
    }

    override fun get(event: Event): Array<Date>? {
        return event.getGuild(guildExpression)
            .map(Guild::getBorn)
            .map(Instant::toEpochMilli)
            .map { born -> Date(born) }
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<Date> {
        return Date::class.java
    }

}