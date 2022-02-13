package pl.funnyskaddon.skript.expression.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.user.User
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.PlayerExpression
import pl.funnyskaddon.skript.getUser

@FunnyDoc
@Name("Player Guild")
@Description("Zwraca gildię gracza")
@Examples(
    "send \"%player guild%\"",
    "set {_guild} to player's guild"
)
class PlayerGuildExpression : PlayerExpression<Guild>("guild of") {

    companion object {
        init {
            Skript.registerExpression(
                PlayerGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "%offlineplayer%['s] guild"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        return event.getUser(playerExpression)
            .flatMap(User::getGuild)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}