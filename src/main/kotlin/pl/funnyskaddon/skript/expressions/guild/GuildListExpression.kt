package pl.funnyskaddon.skript.expressions.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.basic.guild.Guild
import net.dzikoysk.funnyguilds.basic.guild.GuildUtils
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expressions.FunnyExpression

@FunnyDoc
@Name("All Guilds")
@Description("Zwraca wszystkie gildie.")
@Examples(
    "loop all guilds:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildListExpression : FunnyExpression<Guild>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildListExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "[all] guilds [list]"
            )
        }
    }

    override fun get(event: Event): Array<Guild> {
        return GuildUtils.getGuilds().toTypedArray()
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}