package pl.funnyskaddon.skript.expression.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.PlayerExpression
import pl.funnyskaddon.skript.getUser

@FunnyDoc
@Name("Player KDR")
@Description("Zwraca KDR gracza")
@Examples(
    "send \"%player kdr%\"",
    "set {_kdr} to player's kdr"
)
class PlayerKDRExpression : PlayerExpression<Number>("kdr of") {

    companion object {
        init {
            Skript.registerExpression(
                PlayerKDRExpression::class.java,
                Number::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%['s] [(rank|ranking)] (kdr|kills to deaths ratio)"
            )
        }
    }

    override fun get(event: Event): Array<Float> {
        return event.getUser(playerExpression)
            .map { user -> user.rank.kdr }
            .orElse(0F)
            .map { value -> arrayOf(value) }
            .get()

    }

    override fun getReturnType(): Class<Number> {
        return Number::class.javaObjectType
    }

}