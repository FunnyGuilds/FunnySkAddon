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
@Name("Guild Points")
@Description("Zwraca liczbę punktów gildii")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild points%\"",
    "set {_points} to \"FajnaGildia\" guild points"
)
class GuildPointsExpression : GuildExpression<Int>("ranking points of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildPointsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s]  [(rank|ranking)] points [(amount|count)]",
                "%string%['s]  [(rank|ranking)] points [(amount|count)]",
                "%offlineplayer%['s] guild['s]  [(rank|ranking)] points [(amount|count)]",
                "%location%['s] guild['s]  [(rank|ranking)] points [(amount|count)]",
                "%block%['s] guild['s]  [(rank|ranking)] points [(amount|count)]",
                "%object%['s] guild['s]  [(rank|ranking)] points [(amount|count)]",
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuild(guildExpression)
            .map { guild -> guild.rank.averagePoints }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}