package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Lives")
@Description("Zwraca liczbę żyć gildii")
@Examples(
    "send \"%\"\"FajnaGildia\"\" guild lives%\"",
    "set {_lives} to \"FajnaGildia\" guild lives"
)
class GuildLivesExpression : GuildExpression<Int>("lives of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildLivesExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%['s] [guild] lives [(amount|count)]",
                "%guild%['s] lives [(amount|count)]",
                "%string%['s] [guild] lives [(amount|count)]",
                "%offlineplayer%['s] guild['s] lives [(amount|count)]",
                "%location%['s] guild['s] lives [(amount|count)]",
                "%block%['s] guild['s] lives [(amount|count)]",
                "%object%['s] guild['s] lives [(amount|count)]"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        return event.getGuild(guildExpression)
            .map { guild -> guild.lives }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}