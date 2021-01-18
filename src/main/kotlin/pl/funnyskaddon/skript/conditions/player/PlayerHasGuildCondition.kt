package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.PlayerCondition
import pl.funnyskaddon.util.NegationUtil

@FunnyDoc
@Name("Has Guild")
@Description("Sprawdza czy gracz ma gildie")
@Examples(
    "if player has guild:",
    "if player doesn′t hava guild:"
)
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