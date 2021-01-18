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
@Name("Player Points")
@Description("Zwraca liczbe punktów rankingowych gracza")
@Examples(
    "send \"%player points%\"",
    "set {_points} to player's points"
)
class PlayerPointsExpression : PlayerExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerPointsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%(|'s) [rank|ranking] points [amount|count]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        val user = getUser(event)
        var value = 0

        if (user != null && user.rank != null) {
            value = user.rank.points
        }

        return arrayOf(value)
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}