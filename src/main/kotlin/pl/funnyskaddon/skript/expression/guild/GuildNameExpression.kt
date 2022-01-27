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
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Name")
@Description("Zwraca nazwę gildii")
@Examples(
    "send \"%player's guild name%\"",
    "set {_name} to player's guild name"
)
class GuildNameExpression : GuildExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildNameExpression::class.java,
                String::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%['s] guild name"
            )
        }
    }

    override fun get(event: Event): Array<String>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getName)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<String> {
        return String::class.javaObjectType
    }

}