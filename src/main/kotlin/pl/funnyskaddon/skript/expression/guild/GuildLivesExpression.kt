package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Lives")
@Description("Zwraca liczbę żyć gildii")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild lives%\"",
    "set {_lives} to \"FajnaGildia\" guild lives"
)
class GuildLivesExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildLivesExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%['s] guild lives [(amount|count)]"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        return event.getGuildOption(guildExpression)
            .map { guild -> guild.lives }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}