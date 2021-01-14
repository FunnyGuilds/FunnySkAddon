package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.NegationUtil
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.skript.conditions.PlayerCondition

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