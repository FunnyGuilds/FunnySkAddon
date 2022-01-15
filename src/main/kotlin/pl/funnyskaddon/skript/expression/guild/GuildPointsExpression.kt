package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Points")
@Description("Zwraca liczbe punktów gildii")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild points%\"",
    "set {_points} to \"FajnaGildia\" guild points"
)
class GuildPointsExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildPointsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%['s] guild [(rank|ranking)] points [(amount|count)]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuildOption(guildExpression)
            .map { guild -> guild.rank.averagePoints }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}