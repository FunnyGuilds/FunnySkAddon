package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expressions.GuildExpression
import java.util.stream.Collectors

class GuildOnlineMembersExpression : GuildExpression<Player>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildOnlineMembersExpression::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild online deputies"
            )
        }
    }

    override fun get(event: Event): Array<Player>? {
        val guild = getGuild(event)

        if (guild != null) {
            return guild.members.stream()
                .filter { it.offlinePlayer.isOnline }
                .map(User::getPlayer)
                .collect(Collectors.toList())
                .toTypedArray()
        }

        return null
    }

    override fun getReturnType(): Class<Player> {
        return Player::class.java
    }

}