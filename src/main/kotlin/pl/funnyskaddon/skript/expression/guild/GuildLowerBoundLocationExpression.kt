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
import pl.funnyskaddon.extension.getLowerPoint
import pl.funnyskaddon.skript.expression.GuildExpression
import pl.funnyskaddon.skript.getGuildOption

@FunnyDoc
@Name("Guild Lower Bound Location")
@Description("Zwraca dolny skrajny punkt regionu gildii")
@Examples(
    "send \"%\"AC4U\" guild region lower bound%\"",
    "set {_lowerBound} to \"AC4U\" guild region lower bound"
)
class GuildLowerBoundLocationExpression : GuildExpression<Location>("lower bound location of") {

    companion object {
        init {
            Skript.registerExpression(
                GuildLowerBoundLocationExpression::class.java,
                Location::class.java,
                ExpressionType.PROPERTY,
                "%guild%['s] [region] (lower|down) (bound|point) [location]",
                "%string%['s] [region] (lower|down) (bound|point) [location]",
                "%offlineplayer%['s] guild['s] [region] (lower|down) (bound|point) [location]",
                "%location%['s] guild['s] [region] (lower|down) (bound|point) [location]",
                "%block%['s] guild['s] [region] (lower|down) (bound|point) [location]",
                "%object%['s] guild['s] [region] (lower|down) (bound|point) [location]"
            )
        }
    }

    override fun get(event: Event): Array<Location>? {
        return event.getGuildOption(guildExpression)
            .map(Guild::getLowerPoint)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun getReturnType(): Class<Location> {
        return Location::class.java
    }

}