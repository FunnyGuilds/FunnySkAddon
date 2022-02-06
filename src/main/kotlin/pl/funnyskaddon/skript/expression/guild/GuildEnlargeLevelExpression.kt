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
@Name("Guild Enlarge Level")
@Description("Zwraca poziom powiększenia gildii")
@Examples(
    "send \"%\"AC4U\" guild enlarge level%\"",
    "set {_enlarge} to \"AC4U\" guild enlarge level"
)
class GuildEnlargeLevelExpression : GuildExpression<Int>("enlarge level of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildEnlargeLevelExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%guild%['s] [region] enlarge (level|lvl)",
                "%string%['s] [region] enlarge (level|lvl)",
                "%offlineplayer%['s] guild['s] [region] enlarge (level|lvl)",
                "%location%['s] guild['s] [region] enlarge (level|lvl)",
                "%block%['s] guild['s] [region] enlarge (level|lvl)",
                "%object%['s] guild['s] [region] enlarge (level|lvl)"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        return event.getGuildOption(guildExpression)
            .map { guild -> guild.region.enlarge }
            .orElse(0)
            .map { value -> arrayOf(value) }
            .get()
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}