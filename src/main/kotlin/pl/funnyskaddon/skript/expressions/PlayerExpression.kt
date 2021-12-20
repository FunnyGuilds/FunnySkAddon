package pl.funnyskaddon.skript.expressions

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import panda.std.Option

abstract class PlayerExpression<T> : FunnyExpression<T>() {

    protected lateinit var playerExpression: Expression<OfflinePlayer>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        playerExpression = expression[0] as Expression<OfflinePlayer>
        return true
    }

    fun getOfflinePlayer(event: Event?): OfflinePlayer? {
        return try {
            playerExpression.getSingle(event)
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
            val userOption: Option<User> = FunnyGuilds.getInstance().userManager.findByPlayer(getOfflinePlayer(event))
            if (userOption.isEmpty) {
                return null
            }
            userOption.get()
        } catch (ex: Exception) {
            null
        }
    }

}