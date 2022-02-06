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
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Validity Date")
@Description("Zwraca date do kiedy ważna jest gildia")
@Examples(
    "send \"%\"AC4U\" guild validity date%\"",
    "set {_time} to \"AC4U\" guild validity date"
)
class GuildValidityDateExpression : GuildExpression<Date>("validity date of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildValidityDateExpression::class.java,
                Date::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] validity date",
                "%string%['s] validity date",
                "%offlineplayer%['s] guild['s] validity date",
                "%location%['s] guild['s] validity date",
                "%block%['s] guild['s] validity date",
                "%object%['s] guild['s] validity date",
            )
        }
    }

    override fun get(event: Event): Array<Date>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getValidity)
            .map { validity -> Date(validity) }
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Date> {
        return Date::class.java
    }

}