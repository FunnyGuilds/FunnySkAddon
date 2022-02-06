package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.FunnyExpression

@FunnyDoc
@Name("Money To Create Guild")
@Description("Zwraca pieniądze potrzebne do założenia gildii dla graczy z vipem (odpowiednim uprawnieniem)")
@Examples(
    "send \"%required money to create guild with vip%\"",
    "set {_money} to required money to create guild with vip"
)
class ConfigMoneyForGuildVipExpression : FunnyExpression<Double>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigMoneyForGuildVipExpression::class.java,
                Double::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] money (to create|for) guild [(for|with)] vip"
            )
        }
    }

    override fun get(event: Event): Array<Double> {
        return arrayOf(FunnySkAddon.fgConfiguration.requiredMoneyVip)
    }

    override fun getReturnType(): Class<Double> {
        return Double::class.javaObjectType
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "money required for vip to create a guild"
    }

}