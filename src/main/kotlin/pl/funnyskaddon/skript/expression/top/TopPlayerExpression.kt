package pl.funnyskaddon.skript.expression.top

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.TopExpression
import pl.funnyskaddon.skript.getValueOption

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
        return event.getValueOption(positionExpression)
            .map(Number::toInt)
            .map { position -> FunnyGuilds.getInstance().rankManager.getUser(position).offlinePlayer }
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<OfflinePlayer> {
        return OfflinePlayer::class.java
    }

}