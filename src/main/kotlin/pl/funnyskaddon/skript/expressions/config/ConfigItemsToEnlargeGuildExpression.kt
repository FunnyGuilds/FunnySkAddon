package pl.funnyskaddon.skript.expressions.config

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.skript.expressions.FunnyExpression

class ConfigItemsToEnlargeGuildExpression : FunnyExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsToEnlargeGuildExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to|for) enlarge guild"
            )
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        return FunnySkAddon.fgConfiguration.enlargeItems.toTypedArray()
    }

    override fun getReturnType(): Class<ItemStack> {
        return ItemStack::class.java
    }

}