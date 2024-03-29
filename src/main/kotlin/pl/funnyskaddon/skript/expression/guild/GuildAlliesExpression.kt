package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild
import java.util.stream.Collectors

@FunnyDoc
@Name("Guild Allies")
@Description("Zwraca gildie sojusznicze gildii")
@Examples(
    "loop \"AC4U\" guild allies:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class GuildAlliesExpression : GuildExpression<Guild>("allies of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildAlliesExpression::class.java,
                Guild::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] allies",
                "%string%['s] [guild] allies",
                "%offlineplayer%['s] guild['s] allies",
                "%location%['s] guild['s] allies",
                "%block%['s] guild['s] allies",
                "%object%['s] guild['s] allies"
            )
        }
    }

    override fun get(event: Event): Array<Guild> {
        return event.getGuild(guildExpression).toStream()
            .flatMap(Guild::getAllies)
            .collect(Collectors.toSet())
            .toTypedArray()
    }

    override fun isSingle(): Boolean {
        return false
    }

    override fun getReturnType(): Class<Guild> {
        return Guild::class.java
    }

}