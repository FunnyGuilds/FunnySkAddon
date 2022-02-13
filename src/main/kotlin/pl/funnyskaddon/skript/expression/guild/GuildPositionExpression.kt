package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Top Position")
@Description("Zwraca pozycje gildii w rankingu")
@Examples(
    "send \"%player's guild top position%\"",
    "set {_position} to player's guild top position"
)
class GuildPositionExpression : GuildExpression<Int>("ranking position of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildPositionExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] [(rank|ranking)] position",
                "%string%['s] [(rank|ranking)] position",
                "%offlineplayer%['s] guild['s] [(rank|ranking)] position",
                "%location%['s] guild['s] [(rank|ranking)] position",
                "%block%['s] guild['s] [(rank|ranking)] position",
                "%object%['s] guild['s] [(rank|ranking)] position",
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuild(guildExpression)
            .map { guild -> guild.rank.position }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}