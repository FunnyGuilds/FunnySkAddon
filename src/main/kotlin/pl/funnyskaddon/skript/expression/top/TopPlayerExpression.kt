package pl.funnyskaddon.skript.expression.top

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.rank.DefaultTops
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.TopExpression
import pl.funnyskaddon.skript.getValue

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
        return event.getValue(positionExpression)
            .map(Number::toInt)
            .flatMap { position ->
                FunnyGuilds.getInstance().userRankManager.getUser(
                    DefaultTops.USER_POINTS_TOP,
                    position
                )
            }
            .map(User::getOfflinePlayer)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

    override fun toString(e: Event?, debug: Boolean): String {
        if (e != null) {
            return "player at position ${positionExpression.toString(e, debug)}"
        }
        return "guild at position of top"
    }

}