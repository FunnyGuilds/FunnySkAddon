package pl.funnyskaddon.skript.expressions.config

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.skript.expressions.FunnyExpression
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression

class ConfigMoneyForGuildVipExpression : FunnyExpression<Double>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigMoneyForGuildVipExpression::class.java,
                Double::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] money (to create|for) guild [for|with] vip"
            )
        }
    }

    override fun get(event: Event): Array<Double> {
        return arrayOf(FunnySkAddon.fgConfiguration.requiredMoneyVip)
    }

    override fun getReturnType(): Class<Double> {
        return Double::class.javaObjectType
    }

}