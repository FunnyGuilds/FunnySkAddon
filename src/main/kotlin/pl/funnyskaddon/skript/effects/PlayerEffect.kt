package pl.funnyskaddon.skript.effects

import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.util.GuildUtil

abstract class PlayerEffect<T> : Effect() {

    var player: Expression<OfflinePlayer>? = null
    var value: Expression<T>? = null

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        player = expression[0] as Expression<OfflinePlayer>
        value = expression[1] as Expression<T>
        return true
    }

    fun getOfflinePlayer(event: Event?): OfflinePlayer? {
        return try {
            player?.getSingle(event)
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

    fun getValue(event: Event?): T? {
        return value?.getSingle(event)
    }

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

}