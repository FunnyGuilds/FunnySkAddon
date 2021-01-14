package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.FunnyExpression
import pl.funnyskaddon.skript.expressions.GuildExpression
import pl.funnyskaddon.skript.expressions.PlayerExpression
import java.util.stream.Collectors

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