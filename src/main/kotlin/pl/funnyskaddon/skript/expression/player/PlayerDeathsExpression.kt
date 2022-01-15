package pl.funnyskaddon.skript.expression.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.PlayerExpression
import pl.funnyskaddon.skript.getUserOption

@FunnyDoc
@Name("Player Deaths")
@Description("Zwraca liczbe śmierci gracza")
@Examples(
    "send \"%player deaths%\"",
    "set {_deaths} to player's deaths"
)
class PlayerDeathsExpression : PlayerExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerDeathsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%['s] [(rank|ranking)] deaths [(amount|count)]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getUserOption(playerExpression)
            .map { user -> user.rank.deaths }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}