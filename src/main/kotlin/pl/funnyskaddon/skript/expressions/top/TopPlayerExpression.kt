package pl.funnyskaddon.skript.expressions.top

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.rank.RankManager
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.TopExpression

@FunnyDoc
@Name("Player In Position")
@Description("Zwraca gracza na danej pozycji w rankingu")
@Examples(
    "send \"%player in position 1%\"",
    "set {_playerOnPosition} to player in position 4"
)
class TopPlayerExpression : TopExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                TopPlayerExpression::class.java,
                OfflinePlayer::class.java,
                ExpressionType.PROPERTY,
                "player in position %number% [of (top|rank|ranking)]"
            )
        }
    }

    override fun get(event: Event): Array<OfflinePlayer>? {
        val position = position.getSingle(event)
        var value: OfflinePlayer? = null

        if (position != null) {
            value = RankManager.getInstance().getUser(position.toInt()).offlinePlayer
        }

        if (value != null) {
            return arrayOf(value)
        }
        return null
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}