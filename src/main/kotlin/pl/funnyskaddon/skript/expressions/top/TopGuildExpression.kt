package pl.funnyskaddon.skript.expressions.top

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.rank.RankManager
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.PlayerExpression
import pl.funnyskaddon.skript.expressions.TopExpression

class TopGuildExpression : TopExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                TopGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "player in position %number%"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        val position = position?.getSingle(event)
        var value: Guild? = null

        if (position != null) {
            value = RankManager.getInstance().getGuild(position.toInt())
        }

        if(value != null) {
            return arrayOf(value)
        }
        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}