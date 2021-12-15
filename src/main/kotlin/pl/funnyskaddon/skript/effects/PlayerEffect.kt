package pl.funnyskaddon.skript.effects

import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import panda.std.Option

abstract class PlayerEffect<T>(private var inverted: Boolean) : FunnyEffect() {

    lateinit var player: Expression<OfflinePlayer>
    lateinit var value: Expression<T>

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        if (inverted || matchedPattern > 1) {
            value = expression[0] as Expression<T>
            player = expression[1] as Expression<OfflinePlayer>
        } else {
            value = expression[1] as Expression<T>
            player = expression[0] as Expression<OfflinePlayer>
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
            val userOption: Option<User> = FunnyGuilds.getInstance().userManager.findByPlayer(getOfflinePlayer(event))
            if (userOption.isEmpty) {
                return null
            }
            userOption.get()
        } catch (ex: Exception) {
            null
        }
    }

    fun getValue(event: Event?): T? {
        return value.getSingle(event)
    }

}