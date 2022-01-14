package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption
import pl.funnyskaddon.util.getUpperPoint

@FunnyDoc
@Name("Guild Upper Bound Location")
@Description("Zwraca górny skrajny punkt regionu gildii")
@Examples(
    "send \"%\"AC4U\" guild region upper bound%\"",
    "set {_upperBound} to \"AC4U\" guild region upper bound"
)
class GuildUpperBoundLocationExpression : GuildExpression<Location>() {

    companion object {
        init {
            Skript.registerExpression(
                GuildUpperBoundLocationExpression::class.java,
                Location::class.java,
                ExpressionType.PROPERTY,
                "%object%(|'s) guild [region] (upper|up) (bound|point) [location]"
            )
        }
    }

    override fun get(event: Event): Array<Location>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getUpperPoint)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}