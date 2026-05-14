package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Money To Create Guild")
@Description(
    "Zwraca ilość pieniędzy potrzebnych do założenia gildii dla wskazanego " +
        "zestawu (domyślnie \"default\")."
)
@Examples(
    "send \"%required money to create guild%\"",
    "send \"%required money for guild in set \"vip\"%\""
)
class ConfigMoneyForGuildExpression : GuildItemSetExpression<Double>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigMoneyForGuildExpression::class.java,
                Double::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] money (to create|for) guild [[(with|in|of) set] %-string%]"
            )
        }
    }

    override fun get(event: Event): Array<Double> {
        val set = resolveSet(event) ?: return arrayOf(0.0)
        return arrayOf(set.requiredMoney)
    }

    override fun getReturnType(): Class<Double> = Double::class.javaObjectType

    override fun toString(e: Event?, debug: Boolean): String =
        "money required to create a guild in set ${setNameOrDefault(e, debug)}"

}
