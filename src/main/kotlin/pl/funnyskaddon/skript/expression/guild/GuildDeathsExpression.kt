package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Deaths")
@Description("Zwraca liczbę śmierci gildii (sumę wszystkich śmierci jej członków)")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild deaths%\"",
    "set {_deaths} to \"FajnaGildia\" guild deaths"
)
class GuildDeathsExpression : GuildExpression<Int>("deaths of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildDeathsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] [(rank|ranking)] deaths [(amount|count)]",
                "%string%['s] [(rank|ranking)] deaths [(amount|count)]",
                "%offlineplayer%['s] guild['s] [(rank|ranking)] deaths [(amount|count)]",
                "%location%['s] guild['s] [(rank|ranking)] deaths [(amount|count)]",
                "%block%['s] guild['s] [(rank|ranking)] deaths [(amount|count)]",
                "%object%['s] guild['s] [(rank|ranking)] deaths [(amount|count)]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuild(guildExpression)
            .map { guild -> guild.rank.averageDeaths }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}