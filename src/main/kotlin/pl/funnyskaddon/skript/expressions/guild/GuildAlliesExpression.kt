package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.GuildExpression

@FunnyDoc
@Name("Guild Allies")
@Description("Zwraca gildie sojusznicze gildii")
@Examples(
    "loop \"AC4U\" guild allies:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildAlliesExpression : GuildExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAlliesExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild allies"
            )
        }
    }

    override fun get(event: Event): Array<Guild>? {
        val guild = getGuild(event)

        if (guild != null) {
            return guild.allies.toTypedArray()
        }

        return null
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}