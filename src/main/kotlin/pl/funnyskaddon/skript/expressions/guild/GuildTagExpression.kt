package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression

class GuildTagExpression : GuildExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildTagExpression::class.java,
                String::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild tag"
            )
        }
    }

    override fun get(event: Event): Array<String>? {
        val guild = getGuild(event)

        if(guild != null) {
            return arrayOf(guild.tag)
        }

        return null
    }

    override fun getReturnType(): Class<String> {
        return String::class.javaObjectType
    }

}