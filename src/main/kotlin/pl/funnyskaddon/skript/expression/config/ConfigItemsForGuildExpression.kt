package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.feature.items.gui.GuiItemBuilder
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Items To Create Guild")
@Description(
    "Zwraca przedmioty potrzebne do założenia gildii dla wskazanego zestawu " +
        "(domyślnie \"default\"). W FunnyGuilds 5.x przedmioty są definiowane " +
        "przez zestawy w sekcji items.guildItemSets."
)
@Examples(
    "send \"%required items to create guild%\"",
    "send \"%required items to create guild in set \"vip\"%\"",
    "set {_items::*} to required items to create guild in set \"default\""
)
class ConfigItemsForGuildExpression : GuildItemSetExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsForGuildExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to create|for) guild [[(with|in|of) set] %-string%]"
            )
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        val set = resolveSet(event) ?: return emptyArray()
        val itemsConfig = FunnyGuilds.getInstance().itemsConfiguration

        return set.items.mapNotNull { (key, count) ->
            itemsConfig.getLibraryItem(key)
                .map { def -> GuiItemBuilder.buildSimple(def).apply { amount = count } }
                .orElse(null)
        }.toTypedArray()
    }

    override fun isSingle(): Boolean = false

    override fun getReturnType(): Class<ItemStack> = ItemStack::class.java

    override fun toString(e: Event?, debug: Boolean): String =
        "items required to create a guild in set ${setNameOrDefault(e, debug)}"

}
