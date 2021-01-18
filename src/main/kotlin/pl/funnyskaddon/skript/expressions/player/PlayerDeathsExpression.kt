package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.PlayerExpression

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
                "%offlineplayer%(|'s) [(rank|ranking)] deaths [amount|count]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        val user = getUser(event)
        var value = 0

        if (user != null && user.rank != null) {
            value = user.rank.deaths
        }

        return arrayOf(value)
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}