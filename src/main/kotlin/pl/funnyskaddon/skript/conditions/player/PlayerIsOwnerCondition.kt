package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.PlayerCondition
import pl.funnyskaddon.util.NegationUtil

class PlayerIsOwnerCondition : PlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsOwnerCondition::class.java, "(player |)%offlineplayer% is [guild( |-)]owner",
                "(player |)%offlineplayer% is(n't| not) [guild( |-)]owner"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(User.get(getOfflinePlayer(event)).isOwner, isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}