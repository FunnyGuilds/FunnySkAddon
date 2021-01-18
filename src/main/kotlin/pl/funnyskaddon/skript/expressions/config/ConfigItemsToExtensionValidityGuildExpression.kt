package pl.funnyskaddon.skript.expressions.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.FunnyExpression

@FunnyDoc
@Name("Items To Extension Validity")
@Description("Zwraca przedmioty potrzebne aby zwiększyć ważność gildii")
@Examples(
    "send \"%required items to extension validity%\"",
    "set {_items} to extension validity"
)
class ConfigItemsToExtensionValidityGuildExpression : FunnyExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsToExtensionValidityGuildExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to|for) extension [guild] validity"
            )
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        return FunnySkAddon.fgConfiguration.validityItems.toTypedArray()
    }

    override fun getReturnType(): Class<ItemStack> {
        return ItemStack::class.java
    }

}