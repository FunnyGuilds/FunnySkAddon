package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.skript.registration.Registration.registerExpression
import pl.funnyskaddon.skript.expression.FunnyExpression

@Name("Exp To Create Guild")
@Since("2.0.0")
@Description("Returns the level required to create a guild")
@Examples(
    "send \"%required experience to create guild%\"",
    "set {_exp} to required experience to create guild"
)
class ConfigExpForGuildExpression : FunnyExpression<Int>() {

    companion object {
        init {
            registerExpression<ConfigExpForGuildExpression, Int>(
                ExpressionType.PROPERTY,
                "[required] (exp|experience) (to create|for) guild"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        return arrayOf(ConfigItemsForGuildExpression.defaultItemSet().requiredLevel)
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "experience required to create a guild"
    }

}