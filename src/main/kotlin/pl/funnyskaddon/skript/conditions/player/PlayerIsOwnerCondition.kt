package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.PlayerCondition
import pl.funnyskaddon.skript.getUserOption

@FunnyDoc
@Name("Is Owner")
@Description("Sprawdza czy gracz jest właścicielem gildii")
@Examples(
    "if player is owner:",
    "if player is not owner:"
)
class PlayerIsOwnerCondition : PlayerCondition() {

    companion object {
        init {
            Skript.registerCondition(
                PlayerIsOwnerCondition::class.java, "(player |)%offlineplayer% is [guild( |-)]owner",
                "(player |)%offlineplayer% is(n't| not) [guild( |-)]owner"
            )
        }
    }

    override fun check(event: Event): Boolean {
        return try {
            return event.getUserOption(playerExpression)
                .filter(User::isOwner)
                .isPresent.xor(isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}