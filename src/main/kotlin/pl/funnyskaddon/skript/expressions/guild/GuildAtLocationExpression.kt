package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.ValueExpression
import pl.funnyskaddon.util.GuildUtil

class GuildAtLocationExpression : ValueExpression<Location>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAtLocationExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild at %location%"
            )
        }
    }

    override fun get(event: Event): Array<out Guild>? {
        val location: Location? = getValue(event)

        if (location != null) {
            return GuildUtil.getGuildAtLocation(location)
        }

        return null
    }

}