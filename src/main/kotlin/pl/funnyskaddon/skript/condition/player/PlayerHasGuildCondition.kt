package pl.funnyskaddon.skript.condition.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.condition.PlayerCondition
import pl.funnyskaddon.skript.getUserOption

@FunnyDoc
@Name("Has Guild")
@Description("Sprawdza, czy gracz ma gildie")
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

    override fun check(event: Event): Boolean {
        return event.getUserOption(playerExpression)
            .map(User::hasGuild)
            .orElseGet(false).xor(isNegated)
    }

}