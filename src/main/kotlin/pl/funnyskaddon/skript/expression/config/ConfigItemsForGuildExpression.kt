package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.config.sections.items.GuildItemSet
import net.dzikoysk.funnyguilds.shared.bukkit.ItemUtils
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.FunnyExpression

@FunnyDoc
@Name("Items To Create Guild")
@Description("Zwraca przedmioty potrzebne do założenia gildii")
@Examples(
    "send \"%required items to create guild%\"",
    "set {_items} to required items to create guild"
)
class ConfigItemsForGuildExpression : FunnyExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsForGuildExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to create|for) guild"
            )
        }

        fun defaultItemSet(): GuildItemSet {
            val itemsConfig = FunnyGuilds.getInstance().itemsConfiguration
            return itemsConfig.setsSortedByPriority.firstOrNull { it.permission == null } ?: GuildItemSet()
        }

        fun vipItemSet(): GuildItemSet {
            val itemsConfig = FunnyGuilds.getInstance().itemsConfiguration
            return itemsConfig.setsSortedByPriority.firstOrNull { it.permission != null } ?: defaultItemSet()
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        return ItemUtils.buildRequiredItems(defaultItemSet(), FunnyGuilds.getInstance().itemsConfiguration).toTypedArray()
    }

    override fun isSingle(): Boolean {
        return false
    }

    override fun getReturnType(): Class<ItemStack> {
        return ItemStack::class.java
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return "items required to create a guild"
    }

}
