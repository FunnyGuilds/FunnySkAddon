package pl.funnyskaddon.skript.conditions

import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Event

abstract class PlayerCondition : FunnyCondition() {

    lateinit var player: Expression<OfflinePlayer>

    override fun init(expression: Array<Expression<*>?>, matchedPattern: Int, isDelayed: Kleenean?, parseResult: SkriptParser.ParseResult?): Boolean {
        player = expression[0] as Expression<OfflinePlayer>
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

}