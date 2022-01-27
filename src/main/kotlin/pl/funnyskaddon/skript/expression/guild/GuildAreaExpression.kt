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
import kotlin.math.abs

@FunnyDoc
@Name("Guild Area")
@Description("Zwraca powierzchnie regionu gildii")
@Examples(
    "send \"%\"\"AC4U\"\" guild region area%\"",
    "set {_area} to \"AC4U\" guild region area"
)
class GuildAreaExpression : GuildExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildAreaExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "%object%['s] [guild] [region] area"
            )
        }
    }

    override fun get(event: Event): Array<Int>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getRegion)
            .map { region -> abs((region.lowerX - region.upperX) * (region.lowerZ - region.upperZ)) }
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Int> {
        return Int::class.javaObjectType
    }

}