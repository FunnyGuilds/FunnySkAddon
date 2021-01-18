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
@Name("Guild Lives")
@Description("Zwraca liczbe żyć gildii")
@Examples(
    "send \"%\"Fajna Gildia\" guild lives%\"",
    "set {_lives} to \"Fajna Gildia\" guild lives"
)
class GuildLivesExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildLivesExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild lives [amount|count]"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(guild.lives)
        }

        return null
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}