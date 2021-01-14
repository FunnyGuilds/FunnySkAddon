package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.NegationUtil
import pl.funnyskaddon.skript.conditions.GuildCondition
import pl.funnyskaddon.skript.conditions.PlayerCondition

class PlayerHasGuildCondition : PlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerHasGuildCondition::class.java, "(player |)%offlineplayer% has guild",
                "(player |)%offlineplayer% (does not|doesn't) have guild"
            )
        }
    }

    override fun check(event: Event?): Boolean {
        return try {
            NegationUtil.negation(User.get(getOfflinePlayer(event)).hasGuild(), isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}