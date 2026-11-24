package pl.funnyskaddon.skript.expression.guild

import pl.funnyskaddon.extension.orNull

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.util.Date
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.skript.registration.Registration.registerExpression
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild
import java.time.Instant

@Name("Guild Born")
@Since("2.0.0")
@Description("Returns the birth date of a guild")
@Examples(
    "send \"%\"\"AC4U\"\" guild born date%\"",
    "set {_born} to \"AC4U\" guild born date"
)
class GuildBornExpression : GuildExpression<Date>("born data of") {

    companion object {
        init {
            registerExpression<GuildBornExpression, Date>(
                ExpressionType.PROPERTY,
                "%offlineplayer%['s] guild['s] born [date]",
                "%string%['s] [guild] born [date]",
                "%block%['s] guild['s] born [date]",
                "%location%['s] guild['s] born [date]",
                "%guild%['s] born [date]",
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