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
@Name("Exp To Create Guild")
@Description("Zwraca doświadczenie potrzebne do założenia gildii")
@Examples(
    "send \"%required experience to create guild%\"",
    "set {_exp} to required experience to create guild"
)
class ConfigExpForGuildExpression : FunnyExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigExpForGuildExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] (exp|experience) (to create|for) guild"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return arrayOf(FunnySkAddon.fgConfiguration.requiredExperience)
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}