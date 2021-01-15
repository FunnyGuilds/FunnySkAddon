package pl.funnyskaddon.skript.conditions

import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.util.GuildUtil

abstract class GuildPlayerCondition : FunnyCondition() {

    lateinit var player: Expression<OfflinePlayer>
    lateinit var guild: Expression<Any>

    override fun init(expression: Array<Expression<*>?>, matchedPattern: Int, isDelayed: Kleenean?, parseResult: SkriptParser.ParseResult?): Boolean {
        player = expression[0] as Expression<OfflinePlayer>
        guild = expression[1] as Expression<Any>
        if (parseResult != null) {
            isNegated = ((matchedPattern > 1) xor (parseResult.mark == 1))
        }
        return true
    }

    fun getOfflinePlayer(event: Event?): OfflinePlayer? {
        return try {
            player.getSingle(event)
        } catch (ex: Exception) {
            null
        }
    }

    fun getPlayer(event: Event?): Player? {
        val offlinePlayer: OfflinePlayer? = getOfflinePlayer(event)

        if (offlinePlayer != null && offlinePlayer.isOnline) {
            return offlinePlayer.player
        }

        return null
    }

    fun getUser(event: Event?): User? {
        return try {
            User.get(getOfflinePlayer(event))
        } catch (ex: Exception) {
            null
        }
    }

    fun getGuild(event: Event?): Guild? {
        return try {
            GuildUtil.getGuild(guild.getSingle(event))
        } catch (ex: Exception) {
            null
        }
    }

}