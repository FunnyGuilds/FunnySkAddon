package pl.funnyskaddon.skript.expression.guild

import pl.funnyskaddon.skript.registration.Registration.registerExpression
import pl.funnyskaddon.extension.orNull

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.guild.Guild
import net.dzikoysk.funnyguilds.guild.Region
import org.bukkit.Location
import org.bukkit.event.Event
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuild

@Name("Guild Upper Bound Location")
@Since("2.0.0")
@Description("Returns the upper bound of a guild's region")
@Examples(
    "send \"%\"AC4U\" guild region upper bound%\"",
    "set {_upperBound} to \"AC4U\" guild region upper bound"
)
class GuildUpperBoundLocationExpression : GuildExpression<Location>("upper bound location of") {

    companion object {
        init {
            registerExpression<GuildUpperBoundLocationExpression, Location>(
                ExpressionType.PROPERTY,
                "%guild%['s] [region] (upper|up) (bound|point) [location]",
                "%string%['s] [guild] [region] (upper|up) (bound|point) [location]",
                "%offlineplayer%['s] guild['s] [region] (upper|up) (bound|point) [location]",
                "%location%['s] guild['s] [region] (upper|up) (bound|point) [location]",
                "%block%['s] guild['s] [region] (upper|up) (bound|point) [location]",
                "%object%['s] guild['s] [region] (upper|up) (bound|point) [location]"
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