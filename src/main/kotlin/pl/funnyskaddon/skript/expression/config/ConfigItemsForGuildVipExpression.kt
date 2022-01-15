package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.FunnyExpression

@FunnyDoc
@Name("Items To Create Guild Vip")
@Description("Zwraca przedmioty potrzebne do założenia gildii dla graczy z vipem (odpowiednim uprawnieniem)")
@Examples(
    "send \"%required items to create guild with vip%\"",
    "set {_items} to required items to create guild with vip"
)
class ConfigItemsForGuildVipExpression : FunnyExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsForGuildVipExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to create|for) guild [(for|with)] vip"
            )
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        return FunnySkAddon.fgConfiguration.createItemsVip.toTypedArray()
    }

    override fun getReturnType(): Class<ItemStack> {
        return ItemStack::class.java
    }

}