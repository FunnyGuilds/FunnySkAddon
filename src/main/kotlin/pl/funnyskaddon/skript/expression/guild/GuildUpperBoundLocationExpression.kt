package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@FunnyDoc
@Name("Guild Upper Bound Location")
@Description("Zwraca górny skrajny punkt regionu gildii")
@Examples(
    "send \"%\"AC4U\" guild region upper bound%\"",
    "set {_upperBound} to \"AC4U\" guild region upper bound"
)
class GuildUpperBoundLocationExpression : GuildExpression<Location>("upper bound location of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildUpperBoundLocationExpression::class.java,
                Location::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] [region] (upper|up) (bound|point) [location]",
                "%string%['s] [guild] [region] (upper|up) (bound|point) [location]",
                "%offlineplayer%['s] guild['s] [region] (upper|up) (bound|point) [location]",
                "%location%['s] guild['s] [region] (upper|up) (bound|point) [location]",
                "%block%['s] guild['s] [region] (upper|up) (bound|point) [location]",
                "%object%['s] guild['s] [region] (upper|up) (bound|point) [location]",
            )
        }
    }

    override fun get(event: Event): Array<Location>? {
        return event.getGuild(guildExpression)
            .flatMap(Guild::getRegion)
            .map(Region::getUpperCorner)
            .map { value -> arrayOf(value) }
            .orNull()
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}