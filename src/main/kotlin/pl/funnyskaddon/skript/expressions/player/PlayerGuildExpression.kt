package pl.funnyskaddon.skript.expressions.player

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.PlayerExpression

@FunnyDoc
@Name("Player Guild")
@Description("Zwraca gildie gracza.")
@Examples("send \"Gildia gracza: %player guild%\"")
class PlayerGuildExpression : PlayerExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                PlayerGuildExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "%offlineplayer%(|'s) guild"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        val user = getUser(event)
        var value: Guild? = null

        if (user != null) {
            value = user.guild
        }

        if (value != null) {
            return arrayOf(value)
        }
        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}