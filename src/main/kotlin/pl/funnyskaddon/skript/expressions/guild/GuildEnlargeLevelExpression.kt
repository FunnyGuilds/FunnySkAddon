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
@Name("Guild Enlarge Level")
@Description("Zwraca poziom powiększenia gildii")
@Examples(
    "send \"%\"AC4U\" guild enlarge level%\"",
    "set {_enlarge} to \"AC4U\" guild enlarge level"
)
class GuildEnlargeLevelExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildEnlargeLevelExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [region] enlarge (level|lvl)"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        val guild = getGuild(event)

        if (guild != null) {
            return arrayOf(guild.region.enlarge)
        }

        return null
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}