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
@Name("Player Kills")
@Description("Zwraca liczbe zabójstw gracza")
@Examples(
    "send \"%player kills%\"",
    "set {_kills} to player's kills"
)
class PlayerKillsExpression : PlayerExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerKillsExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%offlineplayer%(|'s) [rank|ranking] kills [amount|count]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getUserOption(playerExpression)
            .map { user -> user.rank.kills }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}