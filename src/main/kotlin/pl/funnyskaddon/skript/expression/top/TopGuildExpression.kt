package pl.funnyskaddon.skript.expression.top

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.rank.DefaultTops
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.TopExpression
import pl.funnyskaddon.skript.getValue

@FunnyDoc
@Name("Guild In Position")
@Description("Zwraca gildie na danej pozycji w rankingu")
@Examples(
    "send \"%guild in position 1%\"",
    "set {_guildOnPosition} to guild in position 4"
)
class TopGuildExpression : TopExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                TopGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild in position %number% [of (top|rank|ranking)]"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        return event.getValue(positionExpression)
            .map(Number::toInt)
            .flatMap { position ->
                FunnyGuilds.getInstance().guildRankManager.getGuild(
                    DefaultTops.GUILD_AVG_POINTS_TOP,
                    position
                )
            }
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

    override fun toString(e: Event?, debug: Boolean): String {
        if (e != null) {
            return "guild at position ${positionExpression.toString(e, debug)}"
        }
        return "guild at position of top"
    }

}