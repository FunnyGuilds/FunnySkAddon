package pl.funnyskaddon.skript.expressions.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.FunnyExpression

@FunnyDoc
@Name("Money To Create Guild")
@Description("Zwraca pieniądze potrzebne do założenia gildii")
@Examples(
    "send \"%required money to create guild%\"",
    "set {_money} to required money to create guild"
)
class ConfigMoneyForGuildExpression : FunnyExpression<Double>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigMoneyForGuildExpression::class.java,
                Double::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] money (to create|for) guild"
            )
        }
    }

    override fun get(event: Event): Array<Double> {
        return arrayOf(FunnySkAddon.fgConfiguration.requiredMoney)
    }

    override fun getReturnType(): Class<Double> {
        return Double::class.javaObjectType
    }

}