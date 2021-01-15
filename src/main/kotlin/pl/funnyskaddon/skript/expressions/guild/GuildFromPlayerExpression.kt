package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.ValueExpression

class GuildFromPlayerExpression : ValueExpression<OfflinePlayer>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildFromPlayerExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "guild from player %offlineplayer%"
            )
        }
    }

    override fun get(event: Event): Array<Guild> {
        return arrayOf(User.get(getValue(event)).guild)
    }

}