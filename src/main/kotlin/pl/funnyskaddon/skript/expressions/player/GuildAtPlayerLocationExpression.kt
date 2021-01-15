package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.PlayerExpression
import pl.funnyskaddon.util.GuildUtil

class GuildAtPlayerLocationExpression : PlayerExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAtPlayerLocationExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild at %offlineplayer%(|'s) location"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        val player = getPlayer(event)

        if (player != null) {
            return GuildUtil.getGuildAtLocation(player.location)
        }

        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}