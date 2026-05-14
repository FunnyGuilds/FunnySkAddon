package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Rank To Create Guild")
@Description(
    "Zwraca wymaganą pozycję w rankingu (najgorsza dopuszczalna pozycja) do " +
        "założenia gildii dla wskazanego zestawu (domyślnie \"default\")."
)
@Examples(
    "send \"%required rank to create guild%\"",
    "send \"%required rank for guild in set \"vip\"%\""
)
class ConfigRankForGuildExpression : GuildItemSetExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigRankForGuildExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] rank (to create|for) guild [[(with|in|of) set] %-string%]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        val set = resolveSet(event) ?: return arrayOf(0)
        return arrayOf(set.requiredRank)
    }

    override fun getReturnType(): Class<Int> = Int::class.javaObjectType

    override fun toString(e: Event?, debug: Boolean): String =
        "rank required to create a guild in set ${setNameOrDefault(e, debug)}"

}
