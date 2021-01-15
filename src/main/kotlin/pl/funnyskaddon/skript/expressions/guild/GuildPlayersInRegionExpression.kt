package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.util.getPlayersInGuildRegion

class GuildPlayersInRegionExpression : GuildExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildPlayersInRegionExpression::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "players in %object%(|'s) guild region"
            )
        }
    }

    override fun get(event: Event): Array<Player>? {
        val guild = getGuild(event)

        if(guild != null) {
            return guild.getPlayersInGuildRegion()
        }

        return null
    }

    override fun getReturnType(): Class<Player> {
        return Player::class.java
    }

}