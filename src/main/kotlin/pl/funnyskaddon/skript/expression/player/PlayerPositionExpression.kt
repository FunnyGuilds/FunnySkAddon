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
@Name("Player Top Position")
@Description("Zwraca pozycje gracza w rankingu")
@Examples(
    "send \"%player top position%\"",
    "set {_position} to player's top position"
)
class PlayerPositionExpression : PlayerExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerPositionExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%(|'s) [top|rank|ranking] position"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getUserOption(playerExpression)
            .map { user -> user.rank.position }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}