package pl.funnyskaddon.skript.expressions.config

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.skript.expressions.FunnyExpression

class ConfigItemsToJoinGuildVipExpression : FunnyExpression<ItemStack>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigItemsToJoinGuildVipExpression::class.java,
                ItemStack::class.java,
                ExpressionType.PROPERTY,
                "[required] items (to|for) join guuild"
            )
        }
    }

    override fun get(event: Event): Array<ItemStack> {
        return FunnySkAddon.fgConfiguration.joinItems.toTypedArray()
    }

    override fun getReturnType(): Class<ItemStack> {
        return ItemStack::class.java
    }

}