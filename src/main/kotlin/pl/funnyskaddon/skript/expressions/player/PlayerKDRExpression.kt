package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.PlayerExpression
import pl.funnyskaddon.skript.getUserOption

@FunnyDoc
@Name("Player KDR")
@Description("Zwraca KDR gracza")
@Examples(
    "send \"%player kdr%\"",
    "set {_kdr} to player's kdr"
)
class PlayerKDRExpression : PlayerExpression<Number>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerKDRExpression::class.java,
                Number::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%(|'s) [rank|ranking] (kdr|kills to deaths ratio)"
            )
        }
    }

    override fun get(event: Event): Array<Float> {
        return event.getUserOption(playerExpression)
            .map { user -> user.rank.kdr }
            .orElse(0F)
            .map { kdr -> arrayOf(kdr) }
            .get()

    }

    override fun getReturnType(): Class<Number> {
        return Number::class.javaObjectType
    }

}