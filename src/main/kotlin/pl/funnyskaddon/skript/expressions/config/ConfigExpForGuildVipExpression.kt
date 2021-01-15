package pl.funnyskaddon.skript.expressions.config

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.skript.expressions.FunnyExpression

class ConfigExpForGuildVipExpression : FunnyExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigExpForGuildVipExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] (exp|experience) (to create|for) guild [for|with] vip",
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return arrayOf(FunnySkAddon.fgConfiguration.requiredExperienceVip)
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}