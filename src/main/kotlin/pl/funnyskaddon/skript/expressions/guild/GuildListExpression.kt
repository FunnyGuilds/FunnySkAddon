package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.FunnyExpression

class GuildListExpression : FunnyExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildListExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "[all] guilds"
            )
        }
    }

    override fun get(event: Event): Array<Guild> {
        return GuildUtils.getGuilds().toTypedArray()
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}