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
@Name("Exp To Create Guild Vip")
@Description("Zwraca doświadczenie potrzebne do założenia gildii dla graczy z vipem (odpowiednim uprawnieniem)")
@Examples(
    "send \"%required experience to create guild with vip%\"",
    "set {_exp} to required experience to create guild with vip"
)
class ConfigExpForGuildVipExpression : FunnyExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigExpForGuildVipExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] (exp|experience) (to create|for) guild [(for|with)] vip",
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