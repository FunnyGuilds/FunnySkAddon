package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.rank.DefaultTops
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild
import pl.funnyskaddon.skript.getValue

@FunnyDoc
@Name("Guild Top Position")
@Description("Zwraca pozycje gildii w rankingu")
@Examples(
    "send \"%player's guild top position%\"",
    "set {_position} to player's guild top position"
)
class GuildPositionExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildPositionExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] [(rank|ranking)] position [of %-string%]",
                "%string%['s] [(rank|ranking)] position [of %-string%]",
                "%offlineplayer%['s] guild['s] [(rank|ranking)] position [of %-string%]",
                "%location%['s] guild['s] [(rank|ranking)] position [of %-string%]",
                "%block%['s] guild['s] [(rank|ranking)] position [of %-string%]",
                "%object%['s] guild['s] [(rank|ranking)] position [of %-string%]",
            )
        }
    }

    protected var typeExpression: Expression<String>? = null

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        typeExpression = if (expression.size > 1) expression[1] as Expression<String> else null
        return super.init(expression, matchedPattern, isDelayed, parseResult)
    }

    override fun get(event: Event): Array<Int> {
        return event.getGuild(guildExpression)
            .map { guild ->
                val type: String = event.getValue(typeExpression).orElseGet(DefaultTops.GUILD_AVG_POINTS_TOP)
                guild.rank.getPosition(type)
            }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

    override fun toString(e: Event?, debug: Boolean): String {
        if (e != null) {
            val type: String = e.getValue(typeExpression).orElseGet(DefaultTops.USER_POINTS_TOP)
            return "raking position of guild \"${e.getGuild(guildExpression)}\" in $type top"
        }
        return "ranking position of guild"
    }

}