package pl.funnyskaddon.skript.conditions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.basic.user.User
import org.bukkit.event.Event
import org.panda_lang.utilities.commons.function.Option
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.conditions.PlayerCondition

@FunnyDoc
@Name("Is Deputy")
@Description("Sprawdza czy gracz jest zastępcą gildii")
@Examples(
    "if player is deputy:",
    "if player is not deputy:"
)
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
            val user: Option<User> = FunnyGuilds.getInstance().userManager.getUser(getOfflinePlayer(event))

            if (user.isEmpty) {
                return !isNegated
            }

            user.get().isDeputy.xor(isNegated)
        } catch (ex: Exception) {
            !isNegated
        }
    }

}