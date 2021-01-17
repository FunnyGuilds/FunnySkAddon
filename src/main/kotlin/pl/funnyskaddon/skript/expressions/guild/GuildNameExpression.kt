package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression

@FunnyDoc
@Name("Guild Name")
@Description("Zwraca nazwe gildii")
@Examples("send \"Nazwa gildii gracza %player%: %player's guild name%\"")
class GuildNameExpression : GuildExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildNameExpression::class.java,
                String::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild name"
            )
        }
    }

    override fun get(event: Event): Array<String>? {
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(guild.name)
        }

        return null
    }

    override fun getReturnType(): Class<String> {
        return String::class.javaObjectType
    }

}