package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression

class GuildAlliesExpression : GuildExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAlliesExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild allies"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        val guild = getGuild(event)

        if(guild != null) {
            return guild.allies.toTypedArray()
        }

        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}