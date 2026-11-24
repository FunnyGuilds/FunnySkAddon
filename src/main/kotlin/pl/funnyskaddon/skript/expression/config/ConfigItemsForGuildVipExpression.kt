package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.shared.bukkit.ItemUtils
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.FunnyExpression

@FunnyDoc
@Name("Items To Create Guild (VIP)")
@Description("Zwraca przedmioty potrzebne do założenia gildii przez gracza z VIP-em")
@Examples(
    "send \"%required items to create guild vip%\"",
    "set {_items} to required items to create guild vip"
)
class ConfigItemsForGuildVipExpression : FunnyExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsForGuildVipExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to create|for) guild [with] vip"
            )
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        val itemsConfig = net.dzikoysk.funnyguilds.FunnyGuilds.getInstance().itemsConfiguration
        return ItemUtils.buildRequiredItems(ConfigItemsForGuildExpression.vipItemSet(), itemsConfig).toTypedArray()
    }

    override fun isSingle(): Boolean {
        return false
    }

    override fun getReturnType(): Class<ItemStack> {
        return ItemStack::class.java
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "items required to create a guild with vip"
    }

}
