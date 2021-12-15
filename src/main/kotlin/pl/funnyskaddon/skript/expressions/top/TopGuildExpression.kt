package pl.funnyskaddon.skript.expressions.top

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.rank.RankManager
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.TopExpression

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
        val position = position.getSingle(event)
        var value: Guild? = null

        if (position != null) {
            value = RankManager.getInstance().getGuild(position.toInt())
        }

        if (value != null) {
            return arrayOf(value)
        }
        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}