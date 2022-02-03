package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.SkriptString
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Name")
@Description("Zwraca nazwę gildii")
@Examples(
    "send \"%player's guild name%\"",
    "set {_name} to player's guild name"
)
class GuildNameExpression : GuildExpression<SkriptString>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildNameExpression::class.java,
                SkriptString::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] name",
                "%string%['s] name",
                "%offlineplayer%['s] guild['s] name",
                "%location%['s] guild['s] name",
                "%block%['s] guild['s] name",
                "%object%['s] guild['s] name"
            )
        }
    }

    override fun get(event: Event): Array<SkriptString>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getName)
            .map { SkriptString(it) }
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<SkriptString> {
        return SkriptString::class.java
    }

}