package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.skript.conditions.PlayerCondition
import pl.funnyskaddon.util.NegationUtil

class PlayerIsDeputyCondition : PlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsDeputyCondition::class.java, "(player |)%offlineplayer% is deputy",
                "(player |)%offlineplayer% is(n't| not) deputy"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(User.get(getOfflinePlayer(event)).isDeputy, isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}