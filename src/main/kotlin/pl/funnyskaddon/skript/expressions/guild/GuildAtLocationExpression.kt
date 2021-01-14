package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.Location
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.SpecialGuildExpression
import pl.funnyskaddon.util.GuildUtil

class GuildAtLocationExpression : SpecialGuildExpression<Location>() {

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

        if(location != null) {
            return GuildUtil.getGuildAtLocation(location)
        }

        return null
    }

}